document.addEventListener('DOMContentLoaded', () => {
    // Verificar se o script está sendo carregado
    console.log("Script carregado!");

    // --- CÓDIGO DO NAVBAR ---
    const topNavbar = document.getElementById('top-navbar');
    const mainNavbar = document.getElementById('main-navbar');
    const scrollTrigger = 50;

    // Verificação para garantir que os elementos do navbar existem
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
    } else {
        console.warn('Navbar não encontrado. Ignorando lógica de scroll.');
    }

    // --- CÓDIGO DA BUSCA AJAX ---
    const searchForm = document.getElementById('search-form');
    const contentContainer = document.getElementById('conteudo-atividades');

    if (searchForm && contentContainer) {
        console.log('Formulário de busca e container de conteúdo encontrados. Adicionando event listener.');
        searchForm.addEventListener('submit', (event) => {
            console.log('Evento de submit interceptado.');
            event.preventDefault();

            const formData = new FormData(searchForm);
            const queryString = new URLSearchParams(formData).toString();

            console.log(`Buscando com a query: /buscar?${queryString}`);

            fetch(`/buscar?${queryString}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Erro na requisição: ${response.statusText}`);
                    }
                    return response.text();
                })
                .then(html => {
                    console.log('Conteúdo recebido, injetando no container.');
                    contentContainer.innerHTML = html;
                })
                .catch(error => {
                    console.error('Erro durante a busca AJAX:', error);
                });
        });
    } else {
        console.info('Formulário de busca ou container de conteúdo NÃO foram encontrados no DOM. Isso é esperado em páginas que não são de busca.');
    }

    // --- CÓDIGO DO SELECT2 ---
    // A verificação para o Select2 é feita no $(document).ready para garantir que o JQuery está pronto
    $(document).ready(function() {
        const habilidadesSelect = $('#habilidades');
        if (habilidadesSelect.length) { // Checa se o elemento existe
            $.ajax({
                url: '/api/habilidades',
                dataType: 'json'
            }).done(function(data) {
                $.each(data, function(index, habilidade) {
                    if (habilidadesSelect.find('option[value="' + habilidade.id + '"]').length === 0) {
                        var option = new Option(habilidade.codigoHabilidade + ' - ' + habilidade.etapaEducacional, habilidade.id, false, false);
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
        }
    });

});