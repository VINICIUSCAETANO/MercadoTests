import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prova.Boteco;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BotecoTest {
    @Test
    @DisplayName("O caixa deve iniciar com R$ 1000.00")
    public void testeValorCaixa() {

        //cenário e ação
        Boteco boteco = new Boteco();

        //teste
        assertEquals(1000.0D, boteco.getCaixa());
    }
}
