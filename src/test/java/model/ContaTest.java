package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContaTest {

  static Extrato extrato;
  Conta conta;

  // @BeforeClass -> Junit 4
  @BeforeAll
  public static void init() {
    extrato = mock(Extrato.class);
  }

  // @Before -> Junit 4
  @BeforeEach
  public void setup() {
    conta = new Conta(extrato);
  }

  /**
   * Realiza testes usando mocks
   */
  @Test
  @DisplayName("depósito de valor positivo")
  public void quandoDepositarValorPositivo_entaoAumentaValorNoSaldo() {
    //GIVEN
    BigDecimal valor = BigDecimal.valueOf(100);
//    when(extrato.registrar(TipoOperacao.deposito, valor)).thenReturn(true); // passar objetos reais como parametro
//    when(extrato.registrar(any(), any())).thenReturn(true); // passar quaisquer parametros
    when(extrato.registrar(any(TipoOperacao.class), any())).thenReturn(true); // passar parametros de acordo com o tipo

    //WHEN
    boolean depositou = conta.depositar(valor);

    //THEN
    assertTrue(depositou);
    assertEquals(BigDecimal.valueOf(100), conta.getSaldo());
  }

  @Test
  public void quandoSacarValorPositivo_entaoSubtraiValorDoSaldo() {
    //GIVEN
    conta.depositar(BigDecimal.valueOf(500));
    conta.setAgencia("8888888");

    //WHEN
    conta.sacar(BigDecimal.valueOf(10));

    //THEN
    assertEquals(BigDecimal.valueOf(490), conta.getSaldo());
  }
}
