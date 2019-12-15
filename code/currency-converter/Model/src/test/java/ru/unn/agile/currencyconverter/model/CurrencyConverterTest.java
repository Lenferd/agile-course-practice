package ru.unn.agile.currencyconverter.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyConverterTest {

    private final double delta = 0.01;

    @Test
    public void canCreateConverter() {
        var converter = new EuroRubleConverter();
        assertNotNull(converter);
    }

    @Test
    public void canConvertEuroToRuble() {
        var converter = new EuroRubleConverter();
        var expectedRuble = 69.86;
        assertEquals(expectedRuble, converter.convert(1), delta);
    }

    @Test
    public void canConvertRubleToEuro() {
        var converter = new RubleEuroConverter();
        var expectedEuro = 140;
        assertEquals(expectedEuro, converter.convert(10000), delta);
    }

    @Test
    public void canConvertEuroToDollar() {
        var converter = new EuroDollarConverter();
        var expectedDollar = 112.00000000000001;
        assertEquals(expectedDollar, converter.convert(100), delta);
    }

    @Test
    public void canConvertDollarToEuro() {
        var converter = new DollarEuroConverter();
        var expectedEuro = 8900;
        assertEquals(expectedEuro, converter.convert(10000), delta);
    }

    @Test
    public void canConvertDollarToRuble() {
        var converter = new DollarRubleConverter();
        var expectedRuble = 625.5;
        assertEquals(expectedRuble, converter.convert(10), delta);
    }

    @Test
    public void canConvertRubleToDollar() {
        var converter = new RubleDollarConverter();
        var expectedDollar = 160;
        assertEquals(expectedDollar, converter.convert(10000), delta);
    }

    @Test
    public void cannotConvertNegative() {
        var converter = new RubleDollarConverter();
        var expectedDollar = 160;
        assertEquals(expectedDollar, converter.convert(10000), delta);
    }
}
