package ru.vood.spring.restFullStack.wrapRequest;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Arrays.asList;


public class WrapperForServiceTest {

    final Supplier<List<String>> listSupplierOk = () -> asList("1");
    final Supplier<List<String>> listSupplierErr = () -> {
        if (1 == 1)
            throw new RuntimeException("Error");
        return asList("2");
    };
    final Supplier<String> supplierOk = () -> "1";
    final Supplier<String> supplierErr = () -> {
        if (1 == 1)
            throw new RuntimeException("Error");
        return "2";
    };
    WrapperForService wrapperForService = new WrapperForService() {
    };
    Page page = new Page(15);


    @Test
    public void wrapListNoPageOk() {
        final WrapperForService.WrappedObject<String> stringWr = wrapperForService.wrapList(listSupplierOk);
        Assert.assertEquals(Page.NULL_PAGE, stringWr.getPage());
        Assert.assertArrayEquals(listSupplierOk.get().toArray(), stringWr.getObjectList().toArray());
    }

    @Test(expected = RuntimeException.class)
    public void wrapListNoPageErr() {
        final WrapperForService.WrappedObject<String> stringWr = wrapperForService.wrapList(listSupplierErr);
    }

    @Test
    public void wrapListWithPageOk() {
        final WrapperForService.WrappedObject<String> stringWr = wrapperForService.wrapList(page, listSupplierOk);
        Assert.assertEquals(page, stringWr.getPage());
        Assert.assertArrayEquals(listSupplierOk.get().toArray(), stringWr.getObjectList().toArray());
    }

    @Test(expected = RuntimeException.class)
    public void wrapListWithPageErr() {
        final WrapperForService.WrappedObject<String> stringWr = wrapperForService.wrapList(page, listSupplierErr);
    }

    //--------------------------------------------
    @Test
    public void wrapObjectNoPageOk() {
        final WrapperForService.WrappedObject<String> stringWr = wrapperForService.wrapObject(supplierOk);
        Assert.assertEquals(Page.NULL_PAGE, stringWr.getPage());
        Assert.assertArrayEquals(listSupplierOk.get().toArray(), stringWr.getObjectList().toArray());
    }

    @Test(expected = RuntimeException.class)
    public void wrapObjectNoPageErr() {
        final WrapperForService.WrappedObject<String> stringWr = wrapperForService.wrapObject(supplierErr);
    }

    @Test
    public void wrapObjectWithPageOk() {
        final WrapperForService.WrappedObject<String> stringWr = wrapperForService.wrapObject(page, supplierOk);
        Assert.assertEquals(page, stringWr.getPage());
        Assert.assertArrayEquals(listSupplierOk.get().toArray(), stringWr.getObjectList().toArray());
    }

    @Test(expected = RuntimeException.class)
    public void wrapObjectWithPageErr() {
        final WrapperForService.WrappedObject<String> stringWr = wrapperForService.wrapObject(page, supplierErr);
    }

    @Test
    public void aroundObjectByListOk() {
        final Function<String, List<String>> objectListFunction = wrapperForService.aroundObjectByList();
        final List<String> apply = objectListFunction.apply("1");
        Assert.assertArrayEquals(listSupplierOk.get().toArray(), apply.toArray());
    }

    @Test(expected = RuntimeException.class)
    public void getError() {
        final WrapperForService.WrappedObject<Object> error = wrapperForService.getError(new Exception());
    }

    @Test
    public void getOk() {
        final WrapperForService.WrappedObject<String> ok = wrapperForService.getOk(Page.NULL_PAGE, asList("1"));
        Assert.assertArrayEquals(listSupplierOk.get().toArray(), ok.getObjectList().toArray());
        Assert.assertEquals(Page.NULL_PAGE, ok.getPage());
    }
}