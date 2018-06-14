package Task8;

import org.junit.Test;

public class Task8 extends TestBase {


    @Test
    public void test() {
        app.mainPage.open();
        app.mainPage.openProductByNum(1);
        app.productPage.addToCart();

        app.mainPage.openProductByNum(1);
        app.productPage.addToCart();

        app.mainPage.openProductByNum(1);
        app.productPage.addToCart();

        app.cartPage.open();
        app.cartPage.removeAllProducts();
        app.mainPage.checkThatZeroItemsInCart();

    }

}
