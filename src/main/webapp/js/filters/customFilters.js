/**
 * Created by LINSHIRUI447 on 2016-09-12.
 */
appModule.filter("helloFilter",function(){
    return function(rawData){
        return rawData+"(helloFiltered)";
    }
});