package Task8.test;

import org.junit.Test;

public class Task8 extends TestBase {

    @Test
    public void test() {

        app.mainPage.open();
        for (int i = 1; i < 4; i++) {
            app.mainPage.openProductByNum(i);
            app.productPage.addToCart();
        }
        app.cartPage.removeAllProducts();
        app.mainPage.checkThatZeroItemsInCart();

    }

}
