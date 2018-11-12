var Appearance = (function () {

    var changeActive = function(event) {
        var active = document.getElementsByClassName("active")[0];
        var clicked = document.getElementById(event.srcElement.id);
        active.classList.remove("active");
        clicked.classList.add("active");
    };

    var interval = function () {
        var div = document.getElementById("interval");
        if(!div.contains(document.getElementById("select")) && document.getElementsByClassName("active")[0].id === "intraday") {
            var select = document.createElement("select");
            select.setAttribute("id", "select");
            var opt1 = document.createElement("option");
            opt1.setAttribute("value", "1min");
            var opt1Text = document.createTextNode("1 minute");
            opt1.appendChild(opt1Text);
            var opt5 = document.createElement("option");
            opt5.setAttribute("value", "5min");
            var opt5Text = document.createTextNode("5 minutes");
            opt5.appendChild(opt5Text);
            var opt15 = document.createElement("option");
            opt15.setAttribute("value", "15min");
            var opt15Text = document.createTextNode("15 minutes");
            opt15.appendChild(opt15Text);
            var opt30 = document.createElement("option");
            opt30.setAttribute("value", "30min");
            var opt30Text = document.createTextNode("30 minutes");
            opt30.appendChild(opt30Text);
            var opt60 = document.createElement("option");
            opt60.setAttribute("value", "60min");
            var opt60Text = document.createTextNode("60 minutes");
            opt60.appendChild(opt60Text);
            select.appendChild(opt1);
            select.appendChild(opt5);
            select.appendChild(opt15);
            select.appendChild(opt30);
            select.appendChild(opt60);
            div.appendChild(select);
        } else if(div.contains(document.getElementById("select")) && document.getElementsByClassName("active")[0].id !== "intraday") {
            div.removeChild(div.lastChild);
        }
    };

    return {
        changeActive:changeActive,
        interval:interval
    }

})();