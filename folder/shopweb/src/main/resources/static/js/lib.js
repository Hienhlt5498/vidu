
function getBaseURL() {
    var url = location.href;
    var baseURL = url.substring(0, url.indexOf('/', 14));
    if (baseURL.indexOf('http://localhost') != -1 || baseURL.indexOf('http://buitricong') != -1) {
        // Base Url for localhost
        var url = location.href; // window.location.href;
        var pathname = location.pathname; // window.location.pathname;
        var index1 = url.indexOf(pathname);
        var index2 = url.indexOf("/", index1 + 1);
        var baseLocalUrl = url.substr(0, index2);
        return baseLocalUrl + "/";
    } else {
        // Root Url for domain name
        return baseURL + "/";
    }
}
function ReservationEvent() {
    $("#txtCIDate,#txtCODate").datepicker({
        showOn: "both",
        buttonImage: getBaseURL() + 'Controls/Booking/img/calendar.jpg',
        buttonImageOnly: true,
        dateFormat: 'dd.mm.yy'
    });
}
function Booking() {
    $("#txtCheckIn,#txtCheckOut,#txtArriveDate").datepicker({
        showOn: "both",
        buttonImage: getBaseURL() + 'Controls/Booking/img/calendar-booking.jpg',
        buttonImageOnly: true,
        dateFormat: 'dd.mm.yy'
    });
}

// Share favourite
function fbs_click() {
    var u = location.href;
    var t = document.title;
    window.open('http://www.facebook.com/sharer.php?u=' + encodeURIComponent(u) + '&t=' + encodeURIComponent(t), 'sharer', ',width=980,height=600');
    return false;
}
function zing_click() {
    var u = location.href;
    var t = document.title;
    window.open("http://link.apps.zing.vn/pro/view/conn/share?fl=&u=" + encodeURIComponent(u) + "&amp;t=" + encodeURIComponent(t) + "&amp;desc=" + encodeURIComponent(t));
}

function share_google() {
    var u = location.href;
    var t = document.title;
    window.open("https://www.google.com/bookmarks/mark?op=edit&amp;bkmk=" + encodeURIComponent(u));
}
// validate
function isUserName(value) {
    var re = new RegExp( /^[a-zA-Z0-9_\-]{6,25}$/ );
    if (value.search(re) == -1) {
        return false;
    }
    return true;
}
function isEmail(value) {
    //var re = new RegExp(/^([a-zA-Z][a-zA-Z0-9_\-]*(([a-zA-Z0-9][\.][a-zA-Z0-9][a-zA-Z0-9_\-]*)*)[a-zA-Z0-9]@([a-zA-Z][a-zA-Z0-9_\-]*[a-zA-Z0-9]\.)+([a-zA-Z0-9]{2,4}))$/);
    var re = new RegExp( /^([a-zA-Z0-9][a-zA-Z0-9_\-]*(([\.][a-zA-Z0-9_\-]*)*)[a-zA-Z0-9]@([a-zA-Z0-9][a-zA-Z0-9_\-]*[a-zA-Z0-9]\.)+([a-zA-Z0-9]{2,4}))$/ );
    if (value.search(re) == -1) {
        return false;
    }
    return true;
}

function StringFormat(text) {
    //check if there are two arguments in the arguments list
    if (arguments.length <= 1) {
        //if there are not 2 or more arguments there's nothing to replace
        //just return the original text
        return text;
    }
    //decrement to move to the second argument in the array
    var tokenCount = arguments.length - 2;
    for (var token = 0; token <= tokenCount; token++) {
        //iterate through the tokens and replace their placeholders from the original text in order
        text = text.replace(new RegExp("\\{" + token + "\\}", "gi"), arguments[token + 1]);
    }
    return text;
}