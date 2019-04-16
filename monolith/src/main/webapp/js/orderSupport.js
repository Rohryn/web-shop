function addToCart(productId) {
    $.post("addToCart.do", {
        product_id: productId
    }, function (data) {
        var productCount = data.productCount;
        var totalSum = data.total_sum;
        if (productCount != 0) {
            $("#productsInCard").html("(" + productCount + " items $" + totalSum + ")");
        }
    });
}
function incrementQuantity(productId) {
    $.post("incrementQuantity.do", {
        product_id: productId
    }, function (data) {
        updateElements(data, productId);
        $("#decrementButton_" + productId).attr("disabled", false);
    });
}
function decrementQuantity(productId) {
    $.post("decrementQuantity.do", {
        product_id: productId
    }, function (data) {
        updateElements(data, productId);
        if (data.product_count == 1) {
            $("#decrementButton_" + productId).attr("disabled", true);
        }
    });
}
function removeProduct(productId) {
    $.post("removeProduct.do", {
        product_id: productId
    }, function () {
        $("#decrementButton_" + productId).attr("disabled", false);
        location.reload();
    });
}
function updateElements(data, productId) {
    $("#productQuantity_" + productId).val(data.product_count);
    $("#productSum_" + productId).html(data.product_sum);
    $("#totalSum").html("Total: " + data.total_sum + "$");
}
