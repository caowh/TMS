// /**
//  * Created by Administrator on 2017/7/5.
//  * 获取console.log输出对象，lastLog为最近一次获取的console的对象
//  */
// window.lastLog="";
//  console.oldLog = console.log;
// console.log = function(str) {
//      console.oldLog(str);
//     lastLog = str;
// }
// /**
//  * Created by Administrator on 2017/7/5.
//  * 获取console.error输出对象，lasterr为最近一次获取的console.error的对象
//  */
// window.lasterr="";
// // console.olderr = console.error;
// console.error = function(str) {
//     // console.olderr(str);
//     lasterr = str;
// }
// /**
//  * Created by Administrator on 2017/7/5.
//  * 获取console.lastwarn输出对象，lastwarn为最近一次获取的console.lastwarn的对象
//  */
// window.lastwarn="";
// // console.olderr = console.error;
// console.warn = function(str) {
//     // console.olderr(str);
//     lastwarn = str;
// }
//
//
// // window.historyLog= new Array();
// // console.oldLog = console.debug;
// // console.debug = function(str) {
// //     console.oldLog(str);
// //     historyLog[historyLog.length] = str;
// // }
//
// function isInteger(obj) {
//     return typeof obj === 'number' && obj%1 === 0
// }