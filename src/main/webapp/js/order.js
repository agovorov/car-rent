$(document).ready(function () {
    $("label.has-damage").on("click", function (event) {
        event.preventDefault();
        var $check = $(":checkbox", this);
        $check.prop("checked", !$check.prop("checked"));
        $(".damage_info").toggleClass("show");
    });

    $("button.do-filter").on("click", function () {
        filterVehicle(this);
    });
});


function filterVehicle(modal) {
    var filter = {
        "minPrice": $("#PriceStart").val(),
        "maxPrice": $("#PriceEnd").val(),
        "manufacturers": []
    };

    // Get list of checked manufacturers
    $("div.vehicle-filter-block").find(":checkbox:checked").each(function () {
        var _checkbox = this.value;
        filter.manufacturers.push(_checkbox);
    });

    $(".vehicles-list").empty();
    // Do ajax request
    $.ajax({
        type: "POST",
        url: "/controller?action=order-vehicle-filter",
        data: {"filter": JSON.stringify(filter)},
        dataType: "json"
    }).done(function(data) {
        // Because of jsp use new syntax
        _.templateSettings = {
            interpolate: /\{\{(.+?)\}\}/gim,
            evaluate: /\{\{(.+?)\}\}/gim,
            escape: /\{\{\-(.+?)\}\}/gim
        };

        var days = $('#dDays').val() * 1;
        var template = _.template(
            $("#vehicleBlockTmpl").html()
        );

        $.each(data, function(k, v) {
            v.total_price = days * v.price;
            $(".vehicles-list").append(
                template({item: v})
            );
        })
    }).always(function(){
        // Set label caption
        $('.modal').modal('hide');
    }).error(function(e){
        alert('Error');
    })
}