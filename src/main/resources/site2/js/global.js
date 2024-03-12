var alertMessage =
  '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
  "<strong>An error occurred: </strong> {message}" +
  '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
  "</div>";

String.prototype.formatUnicorn =
  String.prototype.formatUnicorn ||
  function () {
    "use strict";
    var str = this.toString();
    if (arguments.length) {
      var t = typeof arguments[0];
      var key;
      var args =
        "string" === t || "number" === t
          ? Array.prototype.slice.call(arguments)
          : arguments[0];

      for (key in args) {
        str = str.replace(new RegExp("\\{" + key + "\\}", "gi"), args[key]);
      }
    }

    return str;
  };
