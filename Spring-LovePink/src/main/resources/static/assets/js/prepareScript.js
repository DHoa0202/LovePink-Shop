/*
	$(document).ready(function () {
	    $(window).scroll(function () {
	        if ($(this).scrollTop()) {
	            $('navbar').addClass('fixed-top');
	        } else {
	            $('navbar').removeClass('fixed-top');
	        }
	    });
	});
*/

function generalQR(element, qrText) {
    element.innerHTML = null;
    new QRCode(element, {
        text: qrText,
        width: 128,
        height: 128,
        colorDark: "#000000",
        colorLight: "#ffffff",
        correctLevel: QRCode.CorrectLevel.H
	})
}

this.generalQR(document.getElementById('qrCode'), "https://github.com/Team-LovePink/Du_an_tot_nghiep");