var exec = require('cordova/exec');
var PassportReader = function() {
    console.log('PassportReader instanced');
};

PassportReader.prototype.coolMethod = function (arg0, success, error) {
    exec(success, error, 'PassportReader', 'coolMethod', [arg0]);
};

if (typeof module != 'undefined' && module.exports) {
    module.exports = PassportReader;
}