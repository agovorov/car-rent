$(document).ready(function () {
    $('.phone').mask('(000) 000 00 00', {
        placeholder: "(___) ___ __ __",
    });

    $('.date').mask('00.00.0000', {
        placeholder: "__.__.____",
    });

    $('.month').mask('00', {
        placeholder: "__",
    });

    $('.year').mask('0000', {
        placeholder: "____",
    });

    $('.int-4').mask('0000', {
        placeholder: "____",
    });

    $('.card-number').mask('0000 0000 0000 0000', {
        placeholder: "____ ____ ____ ____",
    });

    $('.currency').mask('00000000000', {
        reverse: true
    });
});