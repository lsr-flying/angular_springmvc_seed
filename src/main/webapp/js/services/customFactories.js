/**
 * Created by LINSHIRUI447 on 2016-09-12.
 */
appModule.factory("HelloFactory",function(){
    var privateString = "This is private string in factory";

    function getPrivateString(){
        return privateString;
    }

    return {
        factoryName:"This is HelloFactory",
        getPrivateString:getPrivateString
    }
});