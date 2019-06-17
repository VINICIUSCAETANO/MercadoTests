import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prova.Produto;
import prova.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProdutoTest {

    @Test
    @DisplayName("O estoque nao pode ser negativo")
    public void testaEstoqueNeg() {
        //cenário
        Produto produto = new Produto();

        //ação
        assertThrows(ValidationException.class, () -> produto.cadastrar("asasadasd", "aasasdasd", 2, 3.50, 4.50, -1));
    }

    @Test
    @DisplayName("O estoque nao pode ficar negativo")
    public void testeEstoqueNegativo() {

        //cenário
        Produto produto = new Produto();
        produto.cadastrar("asasadasd", "aasasdasd", 2, 3.50, 4.50, 0);

        //ação
        assertThrows(ValidationException.class, () -> produto.baixarDoEstoque(1));
    }

    @Test
    @DisplayName("O valor de venda não deve ser menor do que o valor de compra")
    public void testeValorVenda() {

        //cenário
        Produto produto = new Produto();

        //ação
        Exception ex = assertThrows(SecurityException.class, () -> produto.cadastrar("asasadasd", "aasasdasd", 2, 2.00, 1.00, 1));

        //teste
        assertEquals("O valor de venda não deve ser menor do que o valor de compra", ex.getMessage());
    }

    @Test
    @DisplayName("O valor de compra não pode ser negativo")
    public void testeValorCompra() {

        //cenário
        Produto produto = new Produto();

        //ação
        Exception ex = assertThrows(ValidationException.class, () -> produto.cadastrar("asasadasd", "aasasdasd", 2, -1.00, 1.00, 1));
    }

    @Test
    @DisplayName("O valor por litro deve ser calculado corretamente")
    public void testeValorLitro() {

        //cenário
        Produto produto = new Produto();

        //ação
        produto.cadastrar("asasadasd", "aasasdasd", 1000, 1.00D, 2, 1);

        //teste
        double valorPLitro = produto.getValorPorLitro();
        assertEquals(2.0 * 1000.00 / 1000.00, valorPLitro);
    }

    @Test
    @DisplayName("nome do Produto deverá possuir entre 3 e 50 caracteres, inclusive")
    public void testeNomeProdutoMenorQue3() {

        //cenário
        Produto produto = new Produto();

        //ação e teste
        assertThrows(ValidationException.class, ()-> produto.cadastrar("asasadasd", "aa", 1, 1.00D, 2, 1));
    }

    @Test
    @DisplayName("nome do Produto deverá possuir entre 3 e 50 caracteres, inclusive")
    public void testeNomeProdutoMaiorIgualQue3() {

        //cenário
        Produto produto = new Produto();

        //ação e teste
        produto.cadastrar("asasadasd", "aad", 1, 1.00D, 2, 1);

        //teste
        assertEquals("aad", produto.getNome());
    }

    @Test
    @DisplayName("nome do Produto deverá possuir entre 3 e 50 caracteres, inclusive")
    public void testeNomeProdutoMenorQue50() {

        //cenário
        Produto produto = new Produto();

        String nome = "";
        for (int i = 1; i <= 50; i++) {
            nome += "a";
        }
        final String nomeProduto = nome;
        //ação e teste
        produto.cadastrar("a", nome, 1, 1.00D, 2, 1);

        //teste
        assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", produto.getNome());
    }

    @Test
    @DisplayName("nome do Produto deverá possuir entre 3 e 50 caracteres, inclusive")
    public void testeNomeProdutoMaiorQue50() {

        //cenário
        Produto produto = new Produto();

        String nome = "";
        for (int i = 1; i <= 51; i++) {
            nome += "a";
        }
        final String nomeProduto = nome;

        //ação
        Exception ex = assertThrows(ValidationException.class, ()-> produto.cadastrar("asasadasd", nomeProduto, 1, 1.00D, 2, 1));

        //teste
        assertEquals("Número de caracteres inválido", ex);
    }
}

