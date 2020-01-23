var exec = require('cordova/exec');
var PassportReader = function() {
    console.log('PassportReader instanced');
};

PassportReader.prototype.readPassport = function (arg0, success, error) {
    exec(success, error, 'PassportReader', 'readPassport', [arg0]);
};

if (typeof module != 'undefined' && module.exports) {
    module.exports = PassportReader;
}