var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'ajhnoti', 'coolMethod', [arg0]);
};

exports.checkNotificationPermission = function (arg0, success, error) {
    //(json)=>{
       // return json.checkNotificationPermission;
   // }
    exec(success, error, 'ajhnoti', 'checkNotificationPermission', [arg0]);

};

exports.test="test";

exports.test1=function(){
    return "Test1";
};