document.addEventListener('DOMContentLoaded', () => {
    console.log("Script carregado!");

    // --- VERIFICAÇÃO DO BOOTSTRAP ---
    if (typeof bootstrap === 'undefined') {
        console.error('Bootstrap não foi carregado corretamente!');
        // Tentar carregar dinamicamente se necessário
        if (typeof $ === 'undefined') {
            console.error('jQuery também não está carregado!');
        }
    } else {
        console.log('Bootstrap carregado:', typeof bootstrap.Modal);
    }

    // --- CÓDIGO DO NAVBAR ---
    const topNavbar = document.getElementById('top-navbar');
    const mainNavbar = document.getElementById('main-navbar');
    const scrollTrigger = 50;

    if (topNavbar && mainNavbar) {
        function handleScroll() {
            const scrollPosition = window.scrollY;
            if (scrollPosition > scrollTrigger) {
                topNavbar.classList.add('hidden');
                mainNavbar.classList.add('scrolled');
            } else {
                topNavbar.classList.remove('hidden');
                mainNavbar.classList.remove('scrolled');
            }
        }
        window.addEventListener('scroll', handleScroll);
        handleScroll(); // Executar uma vez ao carregar
    } else {
        console.warn('Navbar não encontrado.');
    }

    // --- CÓDIGO DA BUSCA AJAX ---
    const searchForm = document.getElementById('search-form');
    const contentContainer = document.getElementById('conteudo-atividades');

    if (searchForm && contentContainer) {
        console.log('Formulário de busca e container encontrados.');
        
        searchForm.addEventListener('submit', (event) => {
            console.log('Evento de submit interceptado.');
            event.preventDefault();

            const formData = new FormData(searchForm);
            const queryString = new URLSearchParams(formData).toString();

            console.log(`Buscando: /buscar?${queryString}`);

            // Mostrar loading
            contentContainer.innerHTML = `
                <div class="text-center py-5">
                    <div class="spinner-border text-primary" role="status">
                        <span class="visually-hidden">Carregando...</span>
                    </div>
                    <p class="mt-2">Buscando atividades...</p>
                </div>
            `;

            fetch(`/buscar?${queryString}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Erro: ${response.statusText}`);
                    }
                    return response.text();
                })
                .then(html => {
                    console.log('Conteúdo recebido.');
                    
                    // Processar HTML recebido
                    const tempDiv = document.createElement('div');
                    tempDiv.innerHTML = html;
                    
                    // Encontrar o conteúdo específico
                    const newContent = tempDiv.querySelector('#conteudo-atividades');
                    
                    if (newContent) {
                        contentContainer.innerHTML = newContent.innerHTML;
                    } else {
                        contentContainer.innerHTML = html;
                    }
                    
                    // Inicializar funcionalidades dos cards
                    initCardsFunctionality();
                    
                    // Log de debug
                    const cardsCount = contentContainer.querySelectorAll('.col').length;
                    console.log(`Cards carregados: ${cardsCount}`);
                    console.log('Botões "Ler mais" encontrados:', document.querySelectorAll('.btn-ler-mais').length);
                })
                .catch(error => {
                    console.error('Erro na busca AJAX:', error);
                    contentContainer.innerHTML = `
                        <div class="alert alert-danger text-center">
                            <h5>Erro ao carregar atividades</h5>
                            <p>Tente novamente ou recarregue a página.</p>
                            <button onclick="location.reload()" class="btn btn-sm btn-outline-danger">
                                Recarregar Página
                            </button>
                        </div>
                    `;
                });
        });
    } else {
        console.info('Busca AJAX não inicializada nesta página.');
    }

    // --- CÓDIGO DO SELECT2 ---
    $(document).ready(function() {
        const habilidadesSelect = $('#habilidades');
        if (habilidadesSelect.length) {
            console.log('Inicializando Select2 para habilidades...');
            
            $.ajax({
                url: '/api/habilidades',
                dataType: 'json'
            }).done(function(data) {
                console.log(`Habilidades carregadas: ${data.length}`);
                
                $.each(data, function(index, habilidade) {
                    if (habilidadesSelect.find('option[value="' + habilidade.id + '"]').length === 0) {
                        var option = new Option(
                            habilidade.codigoHabilidade + ' - ' + habilidade.etapaEducacional, 
                            habilidade.id, 
                            false, 
                            false
                        );
                        $(option).data('descricao', habilidade.descricao);
                        habilidadesSelect.append(option);
                    }
                });

                habilidadesSelect.select2({
                    placeholder: 'Selecione as habilidades',
                    allowClear: true,
                    templateResult: formatOption,
                    templateSelection: formatOption
                });

                function formatOption(option) {
                    if (!option.id) {
                        return option.text;
                    }
                    var descricao = $(option.element).data('descricao');
                    var $option = $('<span>' + option.text + '</span>');
                    if (descricao) {
                        $option.attr('title', descricao);
                    }
                    return $option;
                }
            }).fail(function() {
                console.error('Erro ao carregar habilidades da API');
            });
        }
    });

    // --- FUNCIONALIDADES DOS CARDS ---
    
    // Inicializar tooltips para habilidades
    function initTooltips() {
        const tooltipElements = document.querySelectorAll('.habilidade-tag[title]');
        tooltipElements.forEach(el => {
            try {
                new bootstrap.Tooltip(el, {
                    trigger: 'hover',
                    placement: 'top'
                });
            } catch (e) {
                console.warn('Erro ao criar tooltip:', e);
                // Fallback para tooltip nativo
                el.setAttribute('data-bs-toggle', 'tooltip');
                el.setAttribute('data-bs-placement', 'top');
            }
        });
    }
    
    // Processar descrições longas (cortar texto)
    function processLongDescriptions() {
        document.querySelectorAll('.descricao-text').forEach((desc, index) => {
            const fullText = desc.getAttribute('data-fulltext');
            if (fullText && fullText.length > 120) {
                const shortText = fullText.substring(0, 120) + '...';
                desc.textContent = shortText;
            }
        });
    }
    
    // --- MODAL DE DETALHES DA ATIVIDADE ---
    function initModalFunctionality() {
        console.log('Inicializando funcionalidades do modal...');
        
        // Event listeners para "Ler mais"
        const botoesLerMais = document.querySelectorAll('.btn-ler-mais');
        console.log(`Encontrados ${botoesLerMais.length} botões "Ler mais"`);
        
        botoesLerMais.forEach(btn => {
            // Remover event listeners antigos para evitar duplicação
            btn.removeEventListener('click', handleLerMaisClick);
            btn.addEventListener('click', handleLerMaisClick);
        });
    }
    
    function handleLerMaisClick(e) {
        e.preventDefault();
        console.log('Clicou em "Ler mais"', this);
        abrirModalDetalhes(this);
    }
    
    function abrirModalDetalhes(elemento) {
        console.log('Abrindo modal para elemento:', elemento);
        
        // Coletar dados do card
        const atividadeId = elemento.getAttribute('data-atividade-id');
        const nome = elemento.getAttribute('data-atividade-nome');
        const descricao = elemento.getAttribute('data-atividade-descricao');
        const categoria = elemento.getAttribute('data-atividade-categoria');
        const etapa = elemento.getAttribute('data-atividade-etapa');
        const link = elemento.getAttribute('data-atividade-link');
        const imagem = elemento.getAttribute('data-atividade-imagem');
        
        console.log('Dados coletados:', { nome, categoria, etapa, link });
        
        // Verificar elementos do modal
        const modalConteudo = document.getElementById('modal-conteudo');
        const modalLink = document.getElementById('modal-link-atividade');
        const modalTitle = document.getElementById('modalDetalhesLabel');
        const modalElement = document.getElementById('modalDetalhesAtividade');
        
        console.log('Elementos do modal encontrados:', {
            modalConteudo: !!modalConteudo,
            modalLink: !!modalLink,
            modalTitle: !!modalTitle,
            modalElement: !!modalElement
        });
        
        if (!modalElement) {
            console.error('Modal não encontrado! Verifique se o HTML do modal está incluído.');
            alert('Erro: Modal não encontrado. Recarregue a página.');
            return;
        }
        
        // Configurar título
        if (modalTitle) {
            modalTitle.textContent = nome || 'Detalhes da Atividade';
        }
        
        // Construir HTML do modal
        if (modalConteudo) {
            modalConteudo.innerHTML = `
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <img src="${imagem || 'https://via.placeholder.com/400x300/6c757d/ffffff?text=Sem+Imagem'}" 
                             class="img-fluid rounded" 
                             alt="${nome}"
                             onerror="this.onerror=null; this.src='https://via.placeholder.com/400x300/6c757d/ffffff?text=Sem+Imagem'">
                    </div>
                    <div class="col-md-8">
                        <div class="mb-3">
                            <h6>Descrição Completa:</h6>
                            <p class="text-justify">${descricao || 'Descrição não disponível'}</p>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <h6>Categoria:</h6>
                                <span class="badge bg-light text-dark">${categoria || 'Não informada'}</span>
                            </div>
                            <div class="col-6">
                                <h6>Etapa:</h6>
                                <span class="etapa-badge etapa-${normalizarString(etapa)}">
                                    ${etapa || 'Não informada'}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            `;
        }
        
        // Configurar link
        if (modalLink) {
            modalLink.href = link || '#';
            modalLink.textContent = 'Acessar Atividade';
            if (!link) {
                modalLink.classList.add('disabled');
                modalLink.onclick = (e) => e.preventDefault();
            } else {
                modalLink.classList.remove('disabled');
                modalLink.onclick = null;
            }
        }
        
        // Abrir modal
        try {
            if (typeof bootstrap !== 'undefined' && bootstrap.Modal) {
                const modal = new bootstrap.Modal(modalElement);
                modal.show();
                console.log('Modal aberto com Bootstrap');
            } else if (typeof $ !== 'undefined') {
                // Fallback para jQuery se Bootstrap não estiver disponível
                $(modalElement).modal('show');
                console.log('Modal aberto com jQuery');
            } else {
                // Fallback manual
                modalElement.classList.add('show');
                modalElement.style.display = 'block';
                modalElement.setAttribute('aria-hidden', 'false');
                console.log('Modal aberto manualmente');
            }
        } catch (error) {
            console.error('Erro ao abrir modal:', error);
            // Mostrar conteúdo em alerta como último recurso
            alert(`ERRO: ${nome}\n\n${descricao}\n\nCategoria: ${categoria}\nEtapa: ${etapa}`);
        }
    }
    
    // Função auxiliar para normalizar string (remover acentos)
    function normalizarString(texto) {
        if (!texto) return '';
        return texto.toLowerCase()
            .normalize('NFD')
            .replace(/[\u0300-\u036f]/g, '')
            .replace(/ç/g, 'c')
            .replace(/[^a-z0-9]/g, '-')
            .replace(/-+/g, '-')
            .replace(/^-|-$/g, '');
    }
    
    // Função principal de inicialização dos cards
    function initCardsFunctionality() {
        console.log('Inicializando funcionalidades dos cards...');
        
        // Processar descrições longas
        processLongDescriptions();
        
        // Inicializar modal
        initModalFunctionality();
        
        // Manter tooltips
        initTooltips();
    }
    
    // Inicializar na primeira carga
    setTimeout(() => {
        initCardsFunctionality();
    }, 100); // Pequeno delay para garantir que o DOM está pronto
    
    // Torna as funções disponíveis globalmente (para reuso após AJAX)
    window.initCardsFunctionality = initCardsFunctionality;
    window.abrirModalDetalhes = abrirModalDetalhes;
});

// Função fallback para quando Bootstrap não carrega
if (typeof bootstrap === 'undefined') {
    console.warn('Bootstrap não carregado, usando fallback para modal');
    
    // Simple modal fallback
    window.simpleModal = {
        show: function(modalId) {
            const modal = document.getElementById(modalId);
            if (modal) {
                modal.style.display = 'block';
                modal.classList.add('show');
            }
        },
        hide: function(modalId) {
            const modal = document.getElementById(modalId);
            if (modal) {
                modal.style.display = 'none';
                modal.classList.remove('show');
            }
        }
    };
}