$(document).ready(function () {
    $('label.has-damage').on('click', function (event) {
        event.preventDefault();
        var $check = $(':checkbox', this);
        $check.prop('checked', !$check.prop('checked'));
        $('.damage_info').toggleClass("show");
    });
});
