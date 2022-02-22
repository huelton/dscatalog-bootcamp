package com.devsuperior.dscatalog.exercicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinancingTest {

	@Test
	public void entryShouldBe20percentOfTotalAmount() {
		Double valorFinanciamento = 100000.0;
		Double salarioMensal = 2000.0;
		Integer totalMesesFinanciamento = 80;

		Double valorDeEntrada = 20000.0;

		Financing financing = new Financing(valorFinanciamento, salarioMensal, totalMesesFinanciamento);
		Double result = financing.entry();

		Assertions.assertEquals(valorDeEntrada, result);
	}

	@Test
	public void quotaShouldBe80percentRestOfTotalAmountdividedPerMonths() {
		Double valorFinanciamento = 100000.0;
		Double salarioMensal = 2000.0;
		Integer totalMesesFinanciamento = 80;

		Financing financing = new Financing(valorFinanciamento, salarioMensal, totalMesesFinanciamento);
		Double valorFinanciamentoMensal = financing.quota();

		Assertions.assertEquals(1000.0, valorFinanciamentoMensal);
	}

	@Test
	public void construtorShouldBeValidateWhenvalidateData() {
		Double valorFinanciamento = 100000.0;
		Double salarioMensal = 2000.0;
		Integer totalMesesFinanciamento = 80;

		Financing financing = new Financing(valorFinanciamento, salarioMensal, totalMesesFinanciamento);

		Assertions.assertEquals(100000.0, financing.getTotalAmount());
		Assertions.assertEquals(2000.0, financing.getIncome());
		Assertions.assertEquals(80, financing.getMonths());
	}

	@Test
	public void construtorShouldThrowExceptionWhenInvalidateData() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Double valorFinanciamento = 100000.0;
			Double salarioMensal = 2000.0;
			Integer totalMesesFinanciamento = 79;

			@SuppressWarnings("unused")
			Financing financing = new Financing(valorFinanciamento, salarioMensal, totalMesesFinanciamento);
		});
	}

	@Test
	public void setTotalAmountShouldBeValidateWhenvalidateData() {
		Double valorFinanciamento = 100000.0;
		Double salarioMensal = 2000.0;
		Integer totalMesesFinanciamento = 80;

		Financing financing = new Financing(valorFinanciamento, salarioMensal, totalMesesFinanciamento);
		financing.setTotalAmount(90000.0);

		Assertions.assertEquals(90000.0, financing.getTotalAmount());
	}

	@Test
	public void setTotalAmountShouldThrowExceptionWhenInvalidateData() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Double valorFinanciamento = 100000.0;
			Double salarioMensal = 2000.0;
			Integer totalMesesFinanciamento = 80;

			Financing financing = new Financing(valorFinanciamento, salarioMensal, totalMesesFinanciamento);
			financing.setTotalAmount(110000.0);
		});
	}

	@Test
	public void setIncomeShouldBeValidateWhenvalidateData() {
		Double valorFinanciamento = 100000.0;
		Double salarioMensal = 2000.0;
		Integer totalMesesFinanciamento = 80;

		Financing financing = new Financing(valorFinanciamento, salarioMensal, totalMesesFinanciamento);
		financing.setIncome(3000.0);

		Assertions.assertEquals(3000.0, financing.getIncome());
	}

	@Test
	public void setIncomeShouldThrowExceptionWhenInvalidateData() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Double valorFinanciamento = 100000.0;
			Double salarioMensal = 2000.0;
			Integer totalMesesFinanciamento = 80;

			Financing financing = new Financing(valorFinanciamento, salarioMensal, totalMesesFinanciamento);
			financing.setIncome(1500.0);
		});
	}

	@Test
	public void setMonthsShouldBeValidateWhenvalidateData() {
		Double valorFinanciamento = 100000.0;
		Double salarioMensal = 2000.0;
		Integer totalMesesFinanciamento = 80;

		Financing financing = new Financing(valorFinanciamento, salarioMensal, totalMesesFinanciamento);
		financing.setMonths(81);

		Assertions.assertEquals(81, financing.getMonths());
	}

	@Test
	public void setMonthsShouldThrowExceptionWhenInvalidateData() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Double valorFinanciamento = 110000.0;
			Double salarioMensal = 2000.0;
			Integer totalMesesFinanciamento = 80;

			Financing financing = new Financing(valorFinanciamento, salarioMensal, totalMesesFinanciamento);
			financing.setMonths(79);
		});
	}

}
