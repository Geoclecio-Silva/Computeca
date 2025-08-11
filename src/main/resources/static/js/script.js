document.addEventListener('DOMContentLoaded', () => {
    // Verificar se o script está sendo carregado
    console.log("Script carregado!");

    // Código do Navbar
    const topNavbar = document.getElementById('top-navbar');
    const mainNavbar = document.getElementById('main-navbar');
    const scrollTrigger = 50;

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

    // CÓDIGO AJAX PARA GARANTIR QUE OS ELEMENTOS EXISTAM
    const searchForm = document.getElementById('search-form');
    const contentContainer = document.getElementById('conteudo-atividades');
    
    // Verificar novamente se os elementos existem antes de adicionar o listener
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
    
        console.error('Erro: Formulário de busca ou container de conteúdo NÃO foram encontrados no DOM.');
    }

    
});

$(document).ready(function() {

    // Faz a requisição para o backend e preenche o Select2
    $.ajax({
        url: '/api/habilidades',
        dataType: 'json'
    }).done(function(data) {
        var habilidadesSelect = $('#habilidades');
        
        // Limpa as opções existentes
        habilidadesSelect.empty();
        
        // Adiciona uma nova opção para cada habilidade, guardando a descrição
        $.each(data, function(index, habilidade) {
            var option = new Option(habilidade.codigoHabilidade + ' - ' + habilidade.etapaEducacional, habilidade.id, false, false);
            $(option).data('descricao', habilidade.descricao); // Guarda a descrição aqui
            habilidadesSelect.append(option);
        });

        // Inicializa o Select2 com as funções de template
        habilidadesSelect.select2({
            placeholder: 'Selecione as habilidades',
            allowClear: true,
            templateResult: formatOption,     // Função para exibir as opções na lista
            templateSelection: formatOption  // Função para exibir os itens selecionados
        });
    });

    // Função que formata o texto e adiciona o tooltip
    function formatOption(option) {
        if (!option.id) {
            return option.text;
        }

        var descricao = $(option.element).data('descricao');
        var $option = $('<span>' + option.text + '</span>');
        
        if (descricao) {
            $option.attr('title', descricao); // Adiciona o tooltip aqui
        }
        
        return $option;
    }

});