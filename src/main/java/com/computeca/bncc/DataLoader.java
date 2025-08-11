package com.computeca.bncc;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.computeca.bncc.model.Habilidade;
import com.computeca.bncc.repository.HabilidadeRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final HabilidadeRepository habilidadeRepository;

    public DataLoader(HabilidadeRepository habilidadeRepository) {
        this.habilidadeRepository = habilidadeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se a tabela está vazia. Se não estiver, apaga os dados antigos.
        if (habilidadeRepository.count() > 0) {
            habilidadeRepository.deleteAll();
        }

        //  EDUCAÇÂO INFANTIL

        Habilidade h1 = new Habilidade();
        h1.setEtapaEducacional("Educação Infantil");
        h1.setCodigoHabilidade("EI03CO01");
        h1.setDescricao("Reconhecer padrão de repetição em sequência de sons, movimentos, desenhos.");

        Habilidade h2 = new Habilidade();
        h2.setEtapaEducacional("Educação Infantil");
        h2.setCodigoHabilidade("EI03CO02");
        h2.setDescricao("Expressar as etapas para a realização de uma tarefa de forma clara e ordenada.");

        Habilidade h3 = new Habilidade();
        h3.setEtapaEducacional("Educação Infantil");
        h3.setCodigoHabilidade("EI03CO03");
        h3.setDescricao("Experienciar a execução de algoritmos brincando com objetos (des)plugados.");

        Habilidade h4 = new Habilidade();
        h4.setEtapaEducacional("Educação Infantil");
        h4.setCodigoHabilidade("EI03CO04");
        h4.setDescricao("Criar e representar algoritmos para resolver problemas.");

        Habilidade h5 = new Habilidade();
        h5.setEtapaEducacional("Educação Infantil");
        h5.setCodigoHabilidade("EI03CO05");
        h5.setDescricao("Comparar soluções algorítmicas para resolver um mesmo problema.");

        Habilidade h6 = new Habilidade();
        h6.setEtapaEducacional("Educação Infantil");
        h6.setCodigoHabilidade("EI03CO06");
        h6.setDescricao("Compreender decisões em dois estados (verdadeiro ou falso).");

        Habilidade h7 = new Habilidade();
        h7.setEtapaEducacional("Educação Infantil");
        h7.setCodigoHabilidade("EI03CO07");
        h7.setDescricao("Reconhecer dispositivos eletrônicos (e não-eletrônicos), identificando quando estão ligados ou desligados (abertos ou fechados).");

        Habilidade h8 = new Habilidade();
        h8.setEtapaEducacional("Educação Infantil");
        h8.setCodigoHabilidade("EI03CO08");
        h8.setDescricao("Compreender o conceito de interfaces para comunicação com objetos (des)plugados.");

        Habilidade h9 = new Habilidade();
        h9.setEtapaEducacional("Educação Infantil");
        h9.setCodigoHabilidade("EI03CO09");
        h9.setDescricao("Identificar dispositivos computacionais e as diferentes formas de interação.");

        Habilidade h10 = new Habilidade();
        h10.setEtapaEducacional("Educação Infantil");
        h10.setCodigoHabilidade("EI03CO10");
        h10.setDescricao("Utilizar tecnologia digital de maneira segura, consciente e respeitosa.");

        Habilidade h11 = new Habilidade();
        h11.setEtapaEducacional("Educação Infantil");
        h11.setCodigoHabilidade("EI03CO11");
        h11.setDescricao("Adotar hábitos saudáveis de uso de artefatos computacionais, seguindo recomendações de órgãos de saúde competentes.");

        // FUNDAMENTAL I 1º ANO

        Habilidade h12 = new Habilidade();
        h12.setEtapaEducacional("Ensino Fundamental I - 1º Ano");
        h12.setCodigoHabilidade("EF01CO01");
        h12.setDescricao("Organizar objetos físicos ou digitais considerando diferentes características para esta organização, explicitando semelhanças (padrões) e diferenças.");

        Habilidade h13 = new Habilidade();
        h13.setEtapaEducacional("Ensino Fundamental I - 1º Ano");
        h13.setCodigoHabilidade("EF01CO02");
        h13.setDescricao("Identificar e seguir sequências de passos aplicados no dia a dia para resolver problemas.");

        Habilidade h14 = new Habilidade();
        h14.setEtapaEducacional("Ensino Fundamental I - 1º Ano");
        h14.setCodigoHabilidade("EF01CO03");
        h14.setDescricao("Reorganizar e criar sequências de passos em meios físicos ou digitais, relacionando essas sequências à palavra 'Algoritmos'.");

        Habilidade h15 = new Habilidade();
        h15.setEtapaEducacional("Ensino Fundamental I - 1º Ano");
        h15.setCodigoHabilidade("EF01CO04");
        h15.setDescricao("Reconhecer o que é a informação, que ela pode ser armazenada, transmitida como mensagem por diversos meios e descrita em várias linguagens.");

        Habilidade h16 = new Habilidade();
        h16.setEtapaEducacional("Ensino Fundamental I - 1º Ano");
        h16.setCodigoHabilidade("EF01CO05");
        h16.setDescricao("Representar informação usando diferentes codificações.");

        Habilidade h17 = new Habilidade();
        h17.setEtapaEducacional("Ensino Fundamental I - 1º Ano");
        h17.setCodigoHabilidade("EF01CO06");
        h17.setDescricao("Reconhecer e explorar artefatos computacionais voltados a atender necessidades pessoais ou coletivas.");

        Habilidade h18 = new Habilidade();
        h18.setEtapaEducacional("Ensino Fundamental I - 1º Ano");
        h18.setCodigoHabilidade("EF01CO07");
        h18.setDescricao("Conhecer as possibilidades de uso seguro das tecnologias computacionais para proteção dos dados pessoais e para garantir a própria segurança.");

        Habilidade h19 = new Habilidade();
        h19.setEtapaEducacional("Ensino Fundamental I - 2º Ano");
        h19.setCodigoHabilidade("EF02CO01");
        h19.setDescricao("Criar e comparar modelos (representações) de objetos, identificando padrões e atributos essenciais.");

        Habilidade h20 = new Habilidade();
        h20.setEtapaEducacional("Ensino Fundamental I - 2º Ano");
        h20.setCodigoHabilidade("EF02CO02");
        h20.setDescricao("Criar e simular algoritmos representados em linguagem oral, escrita ou pictográfica, construídos como sequências com repetições simples (iterações definidas) com base em instruções preestabelecidas ou criadas, analisando como a precisão da instrução impacta na execução do algoritmo.");

        Habilidade h21 = new Habilidade();
        h21.setEtapaEducacional("Ensino Fundamental I - 2º Ano");
        h21.setCodigoHabilidade("EF02CO03");
        h21.setDescricao("Identificar que máquinas diferentes executam conjuntos próprios de instruções e que podem ser usadas para definir algoritmos.");

        Habilidade h22 = new Habilidade();
        h22.setEtapaEducacional("Ensino Fundamental I - 2º Ano");
        h22.setCodigoHabilidade("EF02CO04");
        h22.setDescricao("Diferenciar componentes físicos (hardware) e programas que fornecem as instruções (software) para o hardware.");

        Habilidade h23 = new Habilidade();
        h23.setEtapaEducacional("Ensino Fundamental I - 2º Ano");
        h23.setCodigoHabilidade("EF02CO05");
        h23.setDescricao("Reconhecer as características e usos das tecnologias computacionais no cotidiano dentro e fora da escola.");

        Habilidade h24 = new Habilidade();
        h24.setEtapaEducacional("Ensino Fundamental I - 2º Ano");
        h24.setCodigoHabilidade("EF02CO06");
        h24.setDescricao("Reconhecer os cuidados com a segurança no uso de dispositivos computacionais.");


        Habilidade h25 = new Habilidade();
        h25.setEtapaEducacional("Ensino Fundamental I - 3º Ano");
        h25.setCodigoHabilidade("EF03CO01");
        h25.setDescricao("Associar os valores 'verdadeiro' e 'falso' a sentenças lógicas que dizem respeito a situações do dia a dia, fazendo uso de termos que indicam negação.");

        Habilidade h26 = new Habilidade();
        h26.setEtapaEducacional("Ensino Fundamental I - 3º Ano");
        h26.setCodigoHabilidade("EF03CO02");
        h26.setDescricao("Criar e simular algoritmos representados em linguagem oral, escrita ou pictográfica, que incluam sequências e repetições simples com condição (iterações indefinidas), para resolver problemas de forma independente e em colaboração.");

        Habilidade h27 = new Habilidade();
        h27.setEtapaEducacional("Ensino Fundamental I - 3º Ano");
        h27.setCodigoHabilidade("EF03CO03");
        h27.setDescricao("Aplicar a estratégia de decomposição para resolver problemas complexos, dividindo esse problema em partes menores, resolvendo-as e combinando suas soluções.");

        Habilidade h28 = new Habilidade();
        h28.setEtapaEducacional("Ensino Fundamental I - 3º Ano");
        h28.setCodigoHabilidade("EF03CO04");
        h28.setDescricao("Relacionar o conceito de informação com o de dado.");

        Habilidade h29 = new Habilidade();
        h29.setEtapaEducacional("Ensino Fundamental I - 3º Ano");
        h29.setCodigoHabilidade("EF03CO05");
        h29.setDescricao("Compreender que dados são estruturados em formatos específicos dependendo da informação armazenada.");

        Habilidade h30 = new Habilidade();
        h30.setEtapaEducacional("Ensino Fundamental I - 3º Ano");
        h30.setCodigoHabilidade("EF03CO06");
        h30.setDescricao("Reconhecer que, para um computador realizar tarefas, ele se comunica com o mundo exterior com o uso de interfaces físicas (dispositivos de entrada e saída).");

        Habilidade h31 = new Habilidade();
        h31.setEtapaEducacional("Ensino Fundamental I - 3º Ano");
        h31.setCodigoHabilidade("EF03CO07");
        h31.setDescricao("Utilizar diferentes navegadores e ferramentas de busca para pesquisar e acessar informações.");

        Habilidade h32 = new Habilidade();
        h32.setEtapaEducacional("Ensino Fundamental I - 3º Ano");
        h32.setCodigoHabilidade("EF03CO08");
        h32.setDescricao("Usar ferramentas computacionais em situações didáticas para se expressar em diferentes formatos digitais.");

        Habilidade h33 = new Habilidade();
        h33.setEtapaEducacional("Ensino Fundamental I - 3º Ano");
        h33.setCodigoHabilidade("EF03CO09");
        h33.setDescricao("Reconhecer o potencial impacto do compartilhamento de informações pessoais ou de seus pares em meio digital.");
        
        Habilidade h34 = new Habilidade();
        h34.setEtapaEducacional("Ensino Fundamental I - 4º Ano");
        h34.setCodigoHabilidade("EF04CO01");
        h34.setDescricao("Reconhecer objetos do mundo real e/ou digital que podem ser representados através de matrizes que estabelecem uma organização na qual cada componente está em uma posição definida por coordenadas, fazendo manipulações simples sobre estas representações.");

        Habilidade h35 = new Habilidade();
        h35.setEtapaEducacional("Ensino Fundamental I - 4º Ano");
        h35.setCodigoHabilidade("EF04CO02");
        h35.setDescricao("Reconhecer objetos do mundo real e/ou digital que podem ser representados através de registros que estabelecem uma organização na qual cada componente é identificado por um nome, fazendo manipulações sobre estas representações.");

        Habilidade h36 = new Habilidade();
        h36.setEtapaEducacional("Ensino Fundamental I - 4º Ano");
        h36.setCodigoHabilidade("EF04CO03");
        h36.setDescricao("Criar e simular algoritmos representados em linguagem oral, escrita ou pictográfica, que incluam sequências e repetições simples e aninhadas (iterações definidas e indefinidas), para resolver problemas de forma independente e em colaboração.");

        Habilidade h37 = new Habilidade();
        h37.setEtapaEducacional("Ensino Fundamental I - 4º Ano");
        h37.setCodigoHabilidade("EF04CO04");
        h37.setDescricao("Entender que para guardar, manipular e transmitir dados deve-se codificá-los de alguma forma que seja compreendida pela máquina (formato digital).");

        Habilidade h38 = new Habilidade();
        h38.setEtapaEducacional("Ensino Fundamental I - 4º Ano");
        h38.setCodigoHabilidade("EF04CO05");
        h38.setDescricao("Codificar diferentes informações para representação em computador (binária, ASCII, atributos de pixel, como RGB etc.).");

        Habilidade h39 = new Habilidade();
        h39.setEtapaEducacional("Ensino Fundamental I - 4º Ano");
        h39.setCodigoHabilidade("EF04CO06");
        h39.setDescricao("Usar diferentes ferramentas computacionais para criação de conteúdo (textos, apresentações, vídeos etc.).");

        Habilidade h40 = new Habilidade();
        h40.setEtapaEducacional("Ensino Fundamental I - 4º Ano");
        h40.setCodigoHabilidade("EF04CO07");
        h40.setDescricao("Demonstrar postura ética nas atividades de coleta, transferência, guarda e uso de dados.");

        Habilidade h41 = new Habilidade();
        h41.setEtapaEducacional("Ensino Fundamental I - 4º Ano");
        h41.setCodigoHabilidade("EF04CO08");
        h41.setDescricao("Reconhecer a importância de verificar a confiabilidade das fontes de informações obtidas na Internet.");
          
        Habilidade h42 = new Habilidade();
        h42.setEtapaEducacional("Ensino Fundamental I - 5º Ano");
        h42.setCodigoHabilidade("EF05CO01");
        h42.setDescricao("Reconhecer objetos do mundo real e/ou digital que podem ser representados através de listas que estabelecem uma organização na qual há um número variável de itens dispostos em sequência, fazendo manipulações simples sobre estas representações.");

        Habilidade h43 = new Habilidade();
        h43.setEtapaEducacional("Ensino Fundamental I - 5º Ano");
        h43.setCodigoHabilidade("EF05CO02");
        h43.setDescricao("Reconhecer objetos do mundo real e digital que podem ser representados através de grafos que estabelecem uma organização com uma quantidade variável de vértices conectados por arestas, fazendo manipulações simples sobre estas representações.");

        Habilidade h44 = new Habilidade();
        h44.setEtapaEducacional("Ensino Fundamental I - 5º Ano");
        h44.setCodigoHabilidade("EF05CO03");
        h44.setDescricao("Realizar operações de negação, conjunção e disjunção sobre sentenças lógicas e valores 'verdadeiro' e 'falso'.");

        Habilidade h45 = new Habilidade();
        h45.setEtapaEducacional("Ensino Fundamental I - 5º Ano");
        h45.setCodigoHabilidade("EF05CO04");
        h45.setDescricao("Criar e simular algoritmos representados em linguagem oral, escrita ou pictográfica, que incluam sequências, repetições e seleções condicionais para resolver problemas de forma independente e em colaboração.");

        Habilidade h46 = new Habilidade();
        h46.setEtapaEducacional("Ensino Fundamental I - 5º Ano");
        h46.setCodigoHabilidade("EF05CO05");
        h46.setDescricao("Identificar os componentes principais de um computador (dispositivos de entrada/saída, processadores e armazenamento).");

        Habilidade h47 = new Habilidade();
        h47.setEtapaEducacional("Ensino Fundamental I - 5º Ano");
        h47.setCodigoHabilidade("EF05CO06");
        h47.setDescricao("Reconhecer que os dados podem ser armazenados em um dispositivo local ou remoto.");

        Habilidade h48 = new Habilidade();
        h48.setEtapaEducacional("Ensino Fundamental I - 5º Ano");
        h48.setCodigoHabilidade("EF05CO07");
        h48.setDescricao("Reconhecer a necessidade de um sistema operacional para a execução de programas e gerenciamento do hardware.");

        Habilidade h49 = new Habilidade();
        h49.setEtapaEducacional("Ensino Fundamental I - 5º Ano");
        h49.setCodigoHabilidade("EF05CO08");
        h49.setDescricao("Acessar as informações na Internet de forma crítica para distinguir os conteúdos confiáveis de não confiáveis.");

        Habilidade h50 = new Habilidade();
        h50.setEtapaEducacional("Ensino Fundamental I - 5º Ano");
        h50.setCodigoHabilidade("EF05CO09");
        h50.setDescricao("Usar informações considerando aplicações e limites dos direitos autorais em diferentes mídias digitais.");

        Habilidade h51 = new Habilidade();
        h51.setEtapaEducacional("Ensino Fundamental I - 5º Ano");
        h51.setCodigoHabilidade("EF05CO10");
        h51.setDescricao("Expressar-se crítica e criativamente na compreensão das mudanças tecnológicas no mundo do trabalho e sobre a evolução da sociedade.");

        Habilidade h52 = new Habilidade();
        h52.setEtapaEducacional("Ensino Fundamental I - 5º Ano");
        h52.setCodigoHabilidade("EF05CO11");
        h52.setDescricao("Identificar a adequação de diferentes tecnologias computacionais na resolução de problemas.");



        //FUNDAMENTAL I - Habilidades Agrupadas (1º ao 5º Ano)

        Habilidade h53 = new Habilidade();
        h53.setEtapaEducacional("Ensino Fundamental I - 1º ao 5º Ano");
        h53.setCodigoHabilidade("EF15CO01");
        h53.setDescricao("Identificar as principais formas de organizar e representar a informação de maneira estruturada (matrizes, registros, listas e grafos) ou não estruturada (números, palavras, valores verdade).");

        Habilidade h54 = new Habilidade();
        h54.setEtapaEducacional("Ensino Fundamental I - 1º ao 5º Ano");
        h54.setCodigoHabilidade("EF15CO02");
        h54.setDescricao("Construir e simular algoritmos, de forma independente ou em colaboração, que resolvam problemas simples e do cotidiano com uso de sequências, seleções condicionais e repetições de instruções.");

        Habilidade h55 = new Habilidade();
        h55.setEtapaEducacional("Ensino Fundamental I - 1º ao 5º Ano");
        h55.setCodigoHabilidade("EF15CO03");
        h55.setDescricao("Realizar operações de negação, conjunção e disjunção sobre sentenças lógicas e valores 'verdadeiro' e 'falso'.");

        Habilidade h56 = new Habilidade();
        h56.setEtapaEducacional("Ensino Fundamental I - 1º ao 5º Ano");
        h56.setCodigoHabilidade("EF15CO04");
        h56.setDescricao("Aplicar a estratégia de decomposição para resolver problemas complexos, dividindo esse problema em partes menores, resolvendo-as e combinando suas soluções.");

        Habilidade h57 = new Habilidade();
        h57.setEtapaEducacional("Ensino Fundamental I - 1º ao 5º Ano");
        h57.setCodigoHabilidade("EF15CO05");
        h57.setDescricao("Codificar a informação de diferentes formas, entendendo a importância desta codificação para o armazenamento, manipulação e transmissão em dispositivos computacionais.");

        Habilidade h58 = new Habilidade();
        h58.setEtapaEducacional("Ensino Fundamental I - 1º ao 5º Ano");
        h58.setCodigoHabilidade("EF15CO06");
        h58.setDescricao("Conhecer os componentes básicos de dispositivos computacionais, entendendo os princípios de seu funcionamento.");

        Habilidade h59 = new Habilidade();
        h59.setEtapaEducacional("Ensino Fundamental I - 1º ao 5º Ano");
        h59.setCodigoHabilidade("EF15CO07");
        h59.setDescricao("Conhecer o conceito de Sistema Operacional e sua importância na integração entre software e hardware.");

        Habilidade h60 = new Habilidade();
        h60.setEtapaEducacional("Ensino Fundamental I - 1º ao 5º Ano");
        h60.setCodigoHabilidade("EF15CO08");
        h60.setDescricao("Reconhecer e utilizar tecnologias computacionais para pesquisar e acessar informações, expressar-se crítica e criativamente e resolver problemas.");

        Habilidade h61 = new Habilidade();
        h61.setEtapaEducacional("Ensino Fundamental I - 1º ao 5º Ano");
        h61.setCodigoHabilidade("EF15CO09");
        h61.setDescricao("Entender que as tecnologias devem ser utilizadas de maneira segura, ética e responsável, respeitando direitos autorais, de imagem e as leis vigentes.");


        // Ensino Fundamental II - 6º Ano


        Habilidade h62 = new Habilidade();
        h62.setEtapaEducacional("Ensino Fundamental II - 6º Ano");
        h62.setCodigoHabilidade("EF06CO01");
        h62.setDescricao("Classificar informações, agrupando-as em coleções (conjuntos) e associando cada coleção a um 'tipo de dado'.");

        Habilidade h63 = new Habilidade();
        h63.setEtapaEducacional("Ensino Fundamental II - 6º Ano");
        h63.setCodigoHabilidade("EF06CO02");
        h63.setDescricao("Elaborar algoritmos que envolvam instruções sequenciais, de repetição e de seleção usando uma linguagem de programação.");

        Habilidade h64 = new Habilidade();
        h64.setEtapaEducacional("Ensino Fundamental II - 6º Ano");
        h64.setCodigoHabilidade("EF06CO03");
        h64.setDescricao("Descrever com precisão a solução de um problema, construindo o programa que implementa a solução descrita.");

        Habilidade h65 = new Habilidade();
        h65.setEtapaEducacional("Ensino Fundamental II - 6º Ano");
        h65.setCodigoHabilidade("EF06CO04");
        h65.setDescricao("Construir soluções de problemas usando a técnica de decomposição e automatizar tais soluções usando uma linguagem de programação.");

        Habilidade h66 = new Habilidade();
        h66.setEtapaEducacional("Ensino Fundamental II - 6º Ano");
        h66.setCodigoHabilidade("EF06CO05");
        h66.setDescricao("Identificar os recursos ou insumos necessários (entradas) para a resolução de problemas, bem como os resultados esperados (saídas), determinando os respectivos tipos de dados, e estabelecendo a definição de problema como uma relação entre entrada e saída.");

        Habilidade h67 = new Habilidade();
        h67.setEtapaEducacional("Ensino Fundamental II - 6º Ano");
        h67.setCodigoHabilidade("EF06CO06");
        h67.setDescricao("Comparar diferentes casos particulares (instâncias) de um mesmo problema, identificando as semelhanças e diferenças entre eles, e criar um algoritmo para resolver todos, fazendo uso de variáveis (parâmetros) para permitir o tratamento de todos os casos de forma genérica.");

        Habilidade h68 = new Habilidade();
        h68.setEtapaEducacional("Ensino Fundamental II - 6º Ano");
        h68.setCodigoHabilidade("EF06CO07");
        h68.setDescricao("Entender o processo de transmissão de dados, como a informação é quebrada em pedaços, transmitida em pacotes através de múltiplos equipamentos, e reconstruída no destino.");

        Habilidade h69 = new Habilidade();
        h69.setEtapaEducacional("Ensino Fundamental II - 6º Ano");
        h69.setCodigoHabilidade("EF06CO08");
        h69.setDescricao("Compreender e utilizar diferentes formas de armazenar, manipular, compactar e recuperar arquivos, documentos e metadados.");

        Habilidade h70 = new Habilidade();
        h70.setEtapaEducacional("Ensino Fundamental II - 6º Ano");
        h70.setCodigoHabilidade("EF06CO09");
        h70.setDescricao("Apresentar conduta e linguagem apropriadas ao se comunicar em ambiente digital, considerando a ética e o respeito.");

        Habilidade h71 = new Habilidade();
        h71.setEtapaEducacional("Ensino Fundamental II - 6º Ano");
        h71.setCodigoHabilidade("EF06CO10");
        h71.setDescricao("Analisar o consumo de tecnologia na sociedade, compreendendo criticamente o caminho da produção dos recursos bem como aspectos ligados à obsolescência e a sustentabilidade.");

        Habilidade h72 = new Habilidade();
        h72.setEtapaEducacional("Ensino Fundamental II - 7º Ano");
        h72.setCodigoHabilidade("EF07CO01");
        h72.setDescricao("Criar soluções de problemas para os quais seja adequado o uso de registros e matrizes unidimensionais para descrever suas informações e automatizá-las usando uma linguagem de programação.");

        Habilidade h73 = new Habilidade();
        h73.setEtapaEducacional("Ensino Fundamental II - 7º Ano");
        h73.setCodigoHabilidade("EF07CO02");
        h73.setDescricao("Analisar programas para detectar e remover erros, ampliando a confiança na sua correção.");

        Habilidade h74 = new Habilidade();
        h74.setEtapaEducacional("Ensino Fundamental II - 7º Ano");
        h74.setCodigoHabilidade("EF07CO03");
        h74.setDescricao("Construir soluções computacionais de problemas de diferentes áreas do conhecimento, de forma individual e colaborativa, selecionando as estruturas de dados e técnicas adequadas, aperfeiçoando e articulando saberes escolares.");

        Habilidade h75 = new Habilidade();
        h75.setEtapaEducacional("Ensino Fundamental II - 7º Ano");
        h75.setCodigoHabilidade("EF07CO04");
        h75.setDescricao("Explorar propriedades básicas de grafos.");

        Habilidade h76 = new Habilidade();
        h76.setEtapaEducacional("Ensino Fundamental II - 7º Ano");
        h76.setCodigoHabilidade("EF07CO05");
        h76.setDescricao("Criar algoritmos fazendo uso da decomposição e do reúso no processo de solução de forma colaborativa e cooperativa e automatizá-los usando uma linguagem de programação.");

        Habilidade h77 = new Habilidade();
        h77.setEtapaEducacional("Ensino Fundamental II - 7º Ano");
        h77.setCodigoHabilidade("EF07CO06");
        h77.setDescricao("Compreender o papel de protocolos para a transmissão de dados.");

        Habilidade h78 = new Habilidade();
        h78.setEtapaEducacional("Ensino Fundamental II - 7º Ano");
        h78.setCodigoHabilidade("EF07CO07");
        h78.setDescricao("Identificar problemas de segurança cibernética e experimentar formas de proteção.");

        Habilidade h79 = new Habilidade();
        h79.setEtapaEducacional("Ensino Fundamental II - 7º Ano");
        h79.setCodigoHabilidade("EF07CO08");
        h79.setDescricao("Demonstrar empatia sobre opiniões divergentes na web.");

        Habilidade h80 = new Habilidade();
        h80.setEtapaEducacional("Ensino Fundamental II - 7º Ano");
        h80.setCodigoHabilidade("EF07CO09");
        h80.setDescricao("Reconhecer e debater sobre cyberbullying.");

        Habilidade h81 = new Habilidade();
        h81.setEtapaEducacional("Ensino Fundamental II - 7º Ano");
        h81.setCodigoHabilidade("EF07CO10");
        h81.setDescricao("Identificar os impactos ambientais do descarte de peças de computadores e eletrônicos, bem como sua relação com a sustentabilidade.");

        Habilidade h82 = new Habilidade();
        h82.setEtapaEducacional("Ensino Fundamental II - 7º Ano");
        h82.setCodigoHabilidade("EF07CO11");
        h82.setDescricao("Criar, documentar e publicar, de forma individual ou colaborativa, produtos (vídeos, podcasts, web sites) usando recursos de tecnologia.");


        Habilidade h83 = new Habilidade();
        h83.setEtapaEducacional("Ensino Fundamental II - 8º Ano");
        h83.setCodigoHabilidade("EF08CO01");
        h83.setDescricao("Criar soluções de problemas para os quais seja adequado o uso de recursão como uma técnica de resolver o problema.");

        Habilidade h84 = new Habilidade();
        h84.setEtapaEducacional("Ensino Fundamental II - 8º Ano");
        h84.setCodigoHabilidade("EF08CO02");
        h84.setDescricao("Criar soluções de problemas para os quais seja adequado o uso de listas para descrever suas informações e automatizá-las usando uma linguagem de programação, empregando ou não a recursão como uma técnica de resolver o problema.");

        Habilidade h85 = new Habilidade();
        h85.setEtapaEducacional("Ensino Fundamental II - 8º Ano");
        h85.setCodigoHabilidade("EF08CO03");
        h85.setDescricao("Utilizar algoritmos clássicos de manipulação sobre listas.");

        Habilidade h86 = new Habilidade();
        h86.setEtapaEducacional("Ensino Fundamental II - 8º Ano");
        h86.setCodigoHabilidade("EF08CO04");
        h86.setDescricao("Construir soluções computacionais de problemas de diferentes áreas do conhecimento, de forma individual e colaborativa, selecionando as estruturas de dados e técnicas adequadas, aperfeiçoando e articulando saberes escolares.");

        Habilidade h87 = new Habilidade();
        h87.setEtapaEducacional("Ensino Fundamental II - 8º Ano");
        h87.setCodigoHabilidade("EF08CO05");
        h87.setDescricao("Compreender os conceitos de paralelismo, concorrência e armazenamento/processamento distribuídos.");

        Habilidade h88 = new Habilidade();
        h88.setEtapaEducacional("Ensino Fundamental II - 8º Ano");
        h88.setCodigoHabilidade("EF08CO06");
        h88.setDescricao("Entender como é a estrutura e funcionamento da internet.");

        Habilidade h89 = new Habilidade();
        h89.setEtapaEducacional("Ensino Fundamental II - 8º Ano");
        h89.setCodigoHabilidade("EF08CO07");
        h89.setDescricao("Compartilhar informações por meio de redes sociais, compreendendo a sua dinâmica de funcionamento, de forma responsável e avaliando sua confiabilidade, considerando o respeito e a ética.");

        Habilidade h90 = new Habilidade();
        h90.setEtapaEducacional("Ensino Fundamental II - 8º Ano");
        h90.setCodigoHabilidade("EF08CO08");
        h90.setDescricao("Distinguir os tipos de dados pessoais que são solicitados em espaços digitais e os riscos associados.");

        Habilidade h91 = new Habilidade();
        h91.setEtapaEducacional("Ensino Fundamental II - 8º Ano");
        h91.setCodigoHabilidade("EF08CO09");
        h91.setDescricao("Analisar criticamente as políticas de termos de uso das redes sociais e demais plataformas.");

        Habilidade h92 = new Habilidade();
        h92.setEtapaEducacional("Ensino Fundamental II - 8º Ano");
        h92.setCodigoHabilidade("EF08CO10");
        h92.setDescricao("Discutir questões sobre segurança e privacidade relacionadas ao uso dos ambientes virtuais.");

        Habilidade h93 = new Habilidade();
        h93.setEtapaEducacional("Ensino Fundamental II - 8º Ano");
        h93.setCodigoHabilidade("EF08CO11");
        h93.setDescricao("Avaliar a precisão, relevância, adequação, abrangência e vieses que ocorrem em fontes de informação eletrônica.");

        Habilidade h94 = new Habilidade();
        h94.setEtapaEducacional("Ensino Fundamental II - 9º Ano");
        h94.setCodigoHabilidade("EF09CO01");
        h94.setDescricao("Criar soluções de problemas para os quais seja adequado o uso de árvores e grafos para descrever suas informações e automatizá-las usando uma linguagem de programação.");

        Habilidade h95 = new Habilidade();
        h95.setEtapaEducacional("Ensino Fundamental II - 9º Ano");
        h95.setCodigoHabilidade("EF09CO02");
        h95.setDescricao("Construir soluções computacionais de problemas de diferentes áreas do conhecimento, de forma individual e colaborativa, selecionando as estruturas de dados e técnicas adequadas, aperfeiçoando e articulando saberes escolares.");

        Habilidade h96 = new Habilidade();
        h96.setEtapaEducacional("Ensino Fundamental II - 9º Ano");
        h96.setCodigoHabilidade("EF09CO03");
        h96.setDescricao("Usar autômatos para descrever comportamentos de forma abstrata automatizando-os através de uma linguagem de programação baseada em eventos.");

        Habilidade h97 = new Habilidade();
        h97.setEtapaEducacional("Ensino Fundamental II - 9º Ano");
        h97.setCodigoHabilidade("EF09CO04");
        h97.setDescricao("Compreender o funcionamento de malwares e outros ataques cibernéticos.");

        Habilidade h98 = new Habilidade();
        h98.setEtapaEducacional("Ensino Fundamental II - 9º Ano");
        h98.setCodigoHabilidade("EF09CO05");
        h98.setDescricao("Analisar técnicas de criptografia para armazenamento e transmissão de dados.");

        Habilidade h99 = new Habilidade();
        h99.setEtapaEducacional("Ensino Fundamental II - 9º Ano");
        h99.setCodigoHabilidade("EF09CO06");
        h99.setDescricao("Analisar problemas sociais de sua cidade e estado a partir de ambientes digitais, propondo soluções.");

        Habilidade h100 = new Habilidade();
        h100.setEtapaEducacional("Ensino Fundamental II - 9º Ano");
        h100.setCodigoHabilidade("EF09CO07");
        h100.setDescricao("Aplicar conhecimentos computacionais para propor soluções aos desafios da atualidade do ser humano em qualquer área.");

        Habilidade h101 = new Habilidade();
        h101.setEtapaEducacional("Ensino Fundamental II - 9º Ano");
        h101.setCodigoHabilidade("EF09CO08");
        h101.setDescricao("Discutir como a distribuição desigual de recursos de computação em uma economia global levanta questões de equidade, acesso e poder.");

        Habilidade h102 = new Habilidade();
        h102.setEtapaEducacional("Ensino Fundamental II - 9º Ano");
        h102.setCodigoHabilidade("EF09CO09");
        h102.setDescricao("Criar ou utilizar conteúdo em meio digital, compreendendo questões éticas relacionadas a direitos autorais e de uso de imagem.");

        Habilidade h103 = new Habilidade();
        h103.setEtapaEducacional("Ensino Fundamental II - 9º Ano");
        h103.setCodigoHabilidade("EF09CO10");
        h103.setDescricao("Avaliar a veracidade, credibilidade e relevância da informação em seus diferentes formatos, sendo capaz de identificar o propósito pelo qual foi disseminada.");

        // Habilidades Agrupadas (6º ao 9º Ano)

        Habilidade h104 = new Habilidade();
        h104.setEtapaEducacional("Ensino Fundamental II - 6º ao 9º Ano");
        h104.setCodigoHabilidade("EF69CO01");
        h104.setDescricao("Classificar informações, agrupando-as em coleções (conjuntos) e associando cada coleção a um 'tipo de dado'.");

        Habilidade h105 = new Habilidade();
        h105.setEtapaEducacional("Ensino Fundamental II - 6º ao 9º Ano");
        h105.setCodigoHabilidade("EF69CO02");
        h105.setDescricao("Elaborar algoritmos que envolvam instruções sequenciais, de repetição e de seleção usando uma linguagem de programação.");

        Habilidade h106 = new Habilidade();
        h106.setEtapaEducacional("Ensino Fundamental II - 6º ao 9º Ano");
        h106.setCodigoHabilidade("EF69CO03");
        h106.setDescricao("Descrever com precisão a solução de um problema, construindo o programa que implementa a solução descrita.");

        Habilidade h107 = new Habilidade();
        h107.setEtapaEducacional("Ensino Fundamental II - 6º ao 9º Ano");
        h107.setCodigoHabilidade("EF69CO04");
        h107.setDescricao("Construir soluções de problemas usando a técnica de decomposição e automatizar tais soluções usando uma linguagem de programação.");

        Habilidade h108 = new Habilidade();
        h108.setEtapaEducacional("Ensino Fundamental II - 6º ao 9º Ano");
        h108.setCodigoHabilidade("EF69CO05");
        h108.setDescricao("Identificar os recursos ou insumos necessários (entradas) para a resolução de problemas, bem como os resultados esperados (saídas), determinando os respectivos tipos de dados, e estabelecendo a definição de problema como uma relação entre entrada e saída.");

        Habilidade h109 = new Habilidade();
        h109.setEtapaEducacional("Ensino Fundamental II - 6º ao 9º Ano");
        h109.setCodigoHabilidade("EF69CO06");
        h109.setDescricao("Comparar diferentes casos particulares (instâncias) de um mesmo problema, identificando as semelhanças e diferenças entre eles, e criar um algoritmo para resolver todos, fazendo uso de variáveis (parámetros) para permitir o tratamento de todos os casos de forma genérica.");

        Habilidade h110 = new Habilidade();
        h110.setEtapaEducacional("Ensino Fundamental II - 6º ao 9º Ano");
        h110.setCodigoHabilidade("EF69CO07");
        h110.setDescricao("Entender o processo de transmissão de dados, como a informação é quebrada em pedaços, transmitida em pacotes através de múltiplos equipamentos, e reconstruída no destino.");

        Habilidade h111 = new Habilidade();
        h111.setEtapaEducacional("Ensino Fundamental II - 6º ao 9º Ano");
        h111.setCodigoHabilidade("EF69CO08");
        h111.setDescricao("Compreender e utilizar diferentes formas de armazenar, manipular, compactar e recuperar arquivos, documentos e metadados.");

        Habilidade h112 = new Habilidade();
        h112.setEtapaEducacional("Ensino Fundamental II - 6º ao 9º Ano");
        h112.setCodigoHabilidade("EF69CO09");
        h112.setDescricao("Compreender os conceitos de paralelismo, concorrência e armazenamento/processamento distribuídos.");

        Habilidade h113 = new Habilidade();
        h113.setEtapaEducacional("Ensino Fundamental II - 6º ao 9º Ano");
        h113.setCodigoHabilidade("EF69CO10");
        h113.setDescricao("Entender como é a estrutura e funcionamento da internet.");

        Habilidade h114 = new Habilidade();
        h114.setEtapaEducacional("Ensino Fundamental II - 6º ao 9º Ano");
        h114.setCodigoHabilidade("EF69CO11");
        h114.setDescricao("Apresentar conduta e linguagem apropriadas ao se comunicar em ambiente digital, considerando a ética e o respeito.");

        Habilidade h115 = new Habilidade();
        h115.setEtapaEducacional("Ensino Fundamental II - 6º ao 9º Ano");
        h115.setCodigoHabilidade("EF69CO12");
        h115.setDescricao("Analisar o consumo de tecnologia na sociedade, compreendendo criticamente o caminho da produção dos recursos bem como aspectos ligados à obsolescência e a sustentabilidade.");

        // Ensino Médio

        Habilidade h116 = new Habilidade();
        h116.setEtapaEducacional("Ensino Médio");
        h116.setCodigoHabilidade("EM13CO01");
        h116.setDescricao("Explorar e construir a solução de problemas por meio da reutilização de partes de soluções existentes.");

        Habilidade h117 = new Habilidade();
        h117.setEtapaEducacional("Ensino Médio");
        h117.setCodigoHabilidade("EM13CO02");
        h117.setDescricao("Explorar e construir a solução de problemas por meio de refinamentos, utilizando diversos níveis de abstração desde a especificação até a implementação.");

        Habilidade h118 = new Habilidade();
        h118.setEtapaEducacional("Ensino Médio");
        h118.setCodigoHabilidade("EM13CO03");
        h118.setDescricao("Identificar o comportamento dos algoritmos no que diz respeito ao consumo de recursos como tempo de execução, espaço de memória e energia, entre outros.");

        Habilidade h119 = new Habilidade();
        h119.setEtapaEducacional("Ensino Médio");
        h119.setCodigoHabilidade("EM13CO04");
        h119.setDescricao("Reconhecer o conceito de metaprogramação como uma forma de generalização na construção de programas, permitindo que algoritmos sejam entrada ou saída para outros algoritmos.");

        Habilidade h120 = new Habilidade();
        h120.setEtapaEducacional("Ensino Médio");
        h120.setCodigoHabilidade("EM13CO05");
        h120.setDescricao("Identificar os limites da Computação para diferenciar o que pode ou não ser automatizado, buscando uma compreensão mais ampla dos limites dos processos mentais envolvidos na resolução de problemas.");

        Habilidade h121 = new Habilidade();
        h121.setEtapaEducacional("Ensino Médio");
        h121.setCodigoHabilidade("EM13CO06");
        h121.setDescricao("Avaliar software levando em consideração diferentes características e métricas associadas.");

        Habilidade h122 = new Habilidade();
        h122.setEtapaEducacional("Ensino Médio");
        h122.setCodigoHabilidade("EM13CO07");
        h122.setDescricao("Compreender as diferentes tecnologias, bem como equipamentos, protocolos e serviços envolvidos no funcionamento de redes de computadores, identificando suas possibilidades de escala e confiabilidade.");

        Habilidade h123 = new Habilidade();
        h123.setEtapaEducacional("Ensino Médio");
        h123.setCodigoHabilidade("EM13CO08");
        h123.setDescricao("Entender como mudanças na tecnologia afetam a segurança, incluindo novas maneiras de preservar sua privacidade e dados pessoais on-line, reportando suspeitas e buscando ajuda em situações de risco.");

        Habilidade h124 = new Habilidade();
        h124.setEtapaEducacional("Ensino Médio");
        h124.setCodigoHabilidade("EM13CO09");
        h124.setDescricao("Identificar tecnologias digitais, sua presença e formas de uso, nas diferentes atividades no mundo do trabalho.");

        Habilidade h125 = new Habilidade();
        h125.setEtapaEducacional("Ensino Médio");
        h125.setCodigoHabilidade("EM13CO10");
        h125.setDescricao("Conhecer os fundamentos da Inteligência Artificial, comparando-a com a inteligência humana, analisando suas potencialidades, riscos e limites.");

        Habilidade h126 = new Habilidade();
        h126.setEtapaEducacional("Ensino Médio");
        h126.setCodigoHabilidade("EM13CO11");
        h126.setDescricao("Criar e explorar modelos computacionais simples para simular e fazer previsões, identificando sua importância no desenvolvimento científico.");

        Habilidade h127 = new Habilidade();
        h127.setEtapaEducacional("Ensino Médio");
        h127.setCodigoHabilidade("EM13CO12");
        h127.setDescricao("Produzir, analisar, gerir e compartilhar informações a partir de dados, utilizando princípios de ciência de dados.");

        Habilidade h128 = new Habilidade();
        h128.setEtapaEducacional("Ensino Médio");
        h128.setCodigoHabilidade("EM13CO13");
        h128.setDescricao("Analisar e utilizar as diferentes formas de representação e consulta a dados em formato digital para pesquisas científicas.");

        Habilidade h129 = new Habilidade();
        h129.setEtapaEducacional("Ensino Médio");
        h129.setCodigoHabilidade("EM13CO14");
        h129.setDescricao("Avaliar a confiabilidade das informações encontradas em meio digital, investigando seus modos de construção e considerando a autoria, a estrutura e o propósito da mensagem.");

        Habilidade h130 = new Habilidade();
        h130.setEtapaEducacional("Ensino Médio");
        h130.setCodigoHabilidade("EM13CO15");
        h130.setDescricao("Analisar a interação entre usuários e artefatos computacionais, abordando aspectos da experiência do usuário e promovendo reflexão sobre a qualidade do uso dos artefatos nas esferas do trabalho, do lazer e do estudo.");

        Habilidade h131 = new Habilidade();
        h131.setEtapaEducacional("Ensino Médio");
        h131.setCodigoHabilidade("EM13CO16");
        h131.setDescricao("Desenvolver projetos com robótica, utilizando artefatos físicos ou simuladores.");

        Habilidade h132 = new Habilidade();
        h132.setEtapaEducacional("Ensino Médio");
        h132.setCodigoHabilidade("EM13CO17");
        h132.setDescricao("Construir redes virtuais de interação e colaboração, favorecendo o desenvolvimento de projetos de forma segura, legal e ética.");

        Habilidade h133 = new Habilidade();
        h133.setEtapaEducacional("Ensino Médio");
        h133.setCodigoHabilidade("EM13CO18");
        h133.setDescricao("Planejar e gerenciar projetos integrados às áreas de conhecimento de forma colaborativa, solucionando problemas, usando diversos artefatos computacionais.");

        Habilidade h134 = new Habilidade();
        h134.setEtapaEducacional("Ensino Médio");
        h134.setCodigoHabilidade("EM13CO19");
        h134.setDescricao("Expor, argumentar e negociar propostas, produtos e serviços, utilizando diferentes mídias e ferramentas digitais.");

        Habilidade h135 = new Habilidade();
        h135.setEtapaEducacional("Ensino Médio");
        h135.setCodigoHabilidade("EM13CO20");
        h135.setDescricao("Criar conteúdos, disponibilizando-os em ambientes virtuais para publicação e compartilhamento, avaliando a confiabilidade e as consequências da disseminação dessas informações.");

        Habilidade h136 = new Habilidade();
        h136.setEtapaEducacional("Ensino Médio");
        h136.setCodigoHabilidade("EM13CO21");
        h136.setDescricao("Comunicar ideias complexas de forma clara por meio de objetos digitais como mapas conceituais, infográficos, hipertextos e outros.");

        Habilidade h137 = new Habilidade();
        h137.setEtapaEducacional("Ensino Médio");
        h137.setCodigoHabilidade("EM13CO22");
        h137.setDescricao("Produzir e publicar conteúdo como textos, imagens, áudios, vídeos e suas associações, bem como ferramentas para sua integração, organização e apresentação, utilizando diferentes mídias digitais.");

        Habilidade h138 = new Habilidade();
        h138.setEtapaEducacional("Ensino Médio");
        h138.setCodigoHabilidade("EM13CO23");
        h138.setDescricao("Analisar criticamente as experiências em comunidades virtuais e as relações advindas da interação e comunicação com outras pessoas, bem como seus impactos na sociedade.");

        Habilidade h139 = new Habilidade();
        h139.setEtapaEducacional("Ensino Médio");
        h139.setCodigoHabilidade("EM13CO24");
        h139.setDescricao("Identificar e reconhecer como as redes sociais e artefatos computacionais em geral interferem na saúde física e mental de seus usuários.");

        Habilidade h140 = new Habilidade();
        h140.setEtapaEducacional("Ensino Médio");
        h140.setCodigoHabilidade("EM13CO25");
        h140.setDescricao("Dialogar em ambientes virtuais com segurança e respeito às diferenças culturais e pessoais, reconhecendo e denunciando atitudes abusivas.");

        Habilidade h141 = new Habilidade();
        h141.setEtapaEducacional("Ensino Médio");
        h141.setCodigoHabilidade("EM13CO26");
        h141.setDescricao("Aplicar os conceitos e pressupostos do direito digital em sua conduta e experiências com o cotidiano da cultura digital, bem como na produção e uso de artefatos computacionais.");



        habilidadeRepository.saveAll(Arrays.asList(
        h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, // Educação Infantil (1-11)
        h12, h13, h14, h15, h16, h17, h18, // 1º Ano (12-18)
        h19, h20, h21, h22, h23, h24, // 2º Ano (19-24)
        h25, h26, h27, h28, h29, h30, h31, h32, h33, // 3º Ano (25-33)
        h34, h35, h36, h37, h38, h39, h40, h41, // 4º Ano (34-41)
        h42, h43, h44, h45, h46, h47, h48, h49, h50, h51, h52, // 5º Ano (42-52)
        h53, h54, h55, h56, h57, h58, h59, h60, h61, // Agrupadas 1º-5º (53-61)
        h62, h63, h64, h65, h66, h67, h68, h69, h70, h71, // 6º Ano (62-71)
        h72, h73, h74, h75, h76, h77, h78, h79, h80, h81, h82, // 7º Ano (72-82)
        h83, h84, h85, h86, h87, h88, h89, h90, h91, h92, h93, // 8º Ano (83-93)
        h94, h95, h96, h97, h98, h99, h100, h101, h102, h103, // 9º Ano (94-103)
        h104, h105, h106, h107, h108, h109, h110, h111, h112, h113, h114, h115, // Agrupadas 6º-9º (104-115)
        h116, h117, h118, h119, h120, h121, h122, h123, h124, h125, // Ensino Médio (116-125)
        h126, h127, h128, h129, h130, h131, h132, h133, h134, h135, // Ensino Médio (126-135)
        h136, h137, h138, h139, h140, h141 // Ensino Médio (136-141)
    ));
    }
}