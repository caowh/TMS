function assertEqual(str1, str2){
    var info={}
    info["message"]=""
    if(str1!=null&&str2!=null){
        if(str1==str2){
            info["result"]="p"
        }else{
            info["result"]="f"
            info["message"]="expect:"+str1+","+"actual:"+str2
        }
    }else {
        info["result"]="f"
        info["message"]="expect:"+str1+","+"actual:"+str2
    }
    return info
}

function assertNotNull(obj){
    var info={}
    info["message"]=""
    if(obj!=null){
        info["result"]="p"
    }else{
        info["result"]="f"
        info["message"]="result is null"
    }
    return info
}

/**
 * 判断两个vp对象各个属性值是否相等
 * @param v1 第一个viewpoint对象
 * @param v2 第二个viewpoint对象
 * @return "p" or "f"
 */
function assertVp(v1,v2){
    var info={}
    info["message"]=""
    if (v1!=null&&v2!=null){
        var b1,b2,b3,b4,b5,b6,between=0.1;
        b1=assertEqual(Math.round(v1.range),Math.round(v2.range)).result;  //单位;米
        Math.abs(Number(v1.lon).toFixed(1)-Number(v2.lon).toFixed(1))<=between?b2="p":b2="f";   //单位;度
        Math.abs(Number(v1.lat).toFixed(1)-Number(v2.lat).toFixed(1))<=between?b3="p":b3="f";   //单位;度
        b4=assertEqual(Math.round(v1.height),Math.round(v2.height)).result  //单位;米
        Math.abs(Number(v1.pitch).toFixed(1)-Number(v2.pitch).toFixed(1))<=between?b5="p":b5="f";   //单位;度
        Math.abs(Number(v1.heading).toFixed(1)-Number(v2.heading).toFixed(1))<=between?b6="p":b6="f";   //单位;度
        if(b1=="p"&&b2=="p"&&b3=="p"&&b4=="p"&&b5=="p"&&b6=="p"){
            info["result"]="p"
        }else{
            info["result"]="f"
            info["message"]="expect:"+ JSON.stringify(v1)+","+"actual:"+JSON.stringify(v2)
        }
    } else {
        info["message"]="expect:"+ JSON.stringify(v1)+","+"actual:"+JSON.stringify(v2)
    }
    return info
}


/**
 * 判断两个focusRegion对象各个属性值是否相等
 * @param f1 第一个focusRegion对象
 * @param f2 第二个focusRegion对象
 * @return "p" or "f"
 */
function assertFr(f1,f2){
    var info={}
    info["message"]=""
    if (f1!=null&&f2!=null){
        var b1=assertEqual(f1.maxLon,f2.maxLon).result
        var b2=assertEqual(f1.minLon,f2.minLon).result
        var b3=assertEqual(f1.minLat,f2.minLat).result
        var b4=assertEqual(f1.maxLat,f2.maxLat).result
        if(b1=="p"&&b2=="p"&&b3=="p"&&b4=="p"){
            info["result"]="p"
        }else{
            info["result"]="f"
            info["message"]="expect:"+ JSON.stringify(f1)+","+"actual:"+JSON.stringify(f2)
        }
    } else {
        info["result"]="f"
        info["message"]="expect:"+ JSON.stringify(f1)+","+"actual:"+JSON.stringify(f2)
    }
    return info
}





/**
 * 判断focusRegion对象各个属性值不为空
 * @param fr focusRegion对象
 * @return "p" or "f"
 */
function assertFrNotNull(fr){
    var info={}
    info["message"]=""
    if (fr!=null){
        var b1=assertNotNull(fr.maxLon).result
        var b2=assertNotNull(fr.minLon).result
        var b3=assertNotNull(fr.minLat).result
        var b4=assertNotNull(fr.minLon).result
        if(b1=="p"&&b2=="p"&&b3=="p"&&b4=="p"){
            info["result"]="p"
        }else{
            info["result"]="f"
            info["message"]="result:"+ JSON.stringify(fr)
        }
    } else {
        info["result"]="f"
        info["message"]="result is null"
    }
    return info
}
/**断言对象是否为空**/
function assertISNull(obj){
    var info={};
    info["message"]="";
    if(!obj){
        info["result"]="p";
        // info["message"]="is "+JSON.stringify(obj);
        info["message"]="is  "+obj;
    }else{
        info["result"]="f";
        // info["message"]="is  "+JSON.stringify(obj);
        info["message"]="is  "+obj;
    }
    return info
}
/**比较结果与预期是否不相等**/
function assertNotEqual(result,expect) {
    var info={};
    info["message"]="";
    if(result!==null&&expect!==null){
        if (expect === result) {
            info["result"]="f";
            info["message"]="expect: "+expect+" , "+"actual:"+result;
        } else {
            info["result"]="p";
            info["message"]="expect: "+expect+" , "+"actual: "+result;
        }
    }
    else {
        info["result"]="f";
        info["message"]="expect: "+expect+" , "+"actual: "+result;
    }
    return info
}

/********比较两个对象**********************/
function assertObjEqual(obj1,obj2) {
    var isEqual= cmp(obj1, obj2)
    var info={};
    if(obj1!==null&&obj2!==null){
        info["message"]="";
        if(isEqual){
            info["result"]="p";
            // info["message"]="obj1 "+JSON.stringify(obj1)+", not equal obj2 "+JSON.stringify(obj2);
            info["message"]="obj1 equal obj2 ";
        }else {
            info["result"]="f";
            info["message"]="obj1 not equal obj2 ";
            // info["message"]="obj1 "+JSON.stringify(obj1)+", not equal obj2 "+JSON.stringify(obj2);
        }
    }
    else {
        info["result"]="f";
        info["message"]="有对象为空:"+"obj1:"+obj1+","+"obj2:"+obj2;
    }
    return info
}

/***比较两个对象cmp方法***/
cmp = function( x, y ) {
    /***If both x and y are null or undefined and exactly the same***/
    if ( x === y ) {
        return true;
    }
    /***If they are not strictly equal, they both need to be Objects***/
    if ( ! ( x instanceof Object ) || ! ( y instanceof Object ) ) {
        return false;
    }
    /***They must have the exact same prototype chain,the closest we can do is
     test the constructor.***/
    if ( x.constructor !== y.constructor ) {
        return false;
    }

    for ( var p in x ) {
        /***Inherited properties were tested using x.constructor === y.constructor***/
        if ( x.hasOwnProperty( p ) ) {
            /***Allows comparing x[ p ] and y[ p ] when set to undefined***/
            if ( ! y.hasOwnProperty( p ) ) {
                return false;
            }
            /***If they have the same strict value or identity then they are equal**/
            if ( x[ p ] === y[ p ] ) {
                continue;
            }
            /***Numbers, Strings, Functions, Booleans must be strictly equal**/
            if ( typeof( x[ p ] ) !== "object" ) {
                return false;
            }
            /***Objects and Arrays must be tested recursively**/
            if ( ! Object.equals( x[ p ], y[ p ] ) ) {
                return false;
            }
        }
    }
    for ( p in y ) {
        /***allows x[ p ] to be set to undefined**/
        if ( y.hasOwnProperty( p ) && ! x.hasOwnProperty( p ) ) {
            return false;
        }
    }
    return true;
};
// function makeJsonFile(arrParse){
//     var fso= new ActiveXObject("Scripting.FileSystemObject");
//     var json=getNowFormatDate()+"result.json"
//     if(!fso.FileExists(json)){
//         var f1 = fso.createtextfile(json,true);
//         tf.write(arrParse);
//         tf.Close();
//     }
// }
//
//
// function getNowFormatDate() {
//     var date = new Date();
//     var seperator1 = "-";
//     var seperator2 = ":";
//     var month = date.getMonth() + 1;
//     var strDate = date.getDate();
//     if (month >= 1 && month <= 9) {
//         month = "0" + month;
//     }
//     if (strDate >= 0 && strDate <= 9) {
//         strDate = "0" + strDate;
//     }
//     var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
//         + " " + date.getHours() + seperator2 + date.getMinutes()
//         + seperator2 + date.getSeconds();
//     return currentdate;
// }