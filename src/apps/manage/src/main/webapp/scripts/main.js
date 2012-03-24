/**
 * Javascript v1.2 for opentravelmsoft.com
 *
 * $Id: main.js,v 1.1 2009/03/01 16:24:29 zhangst Exp $
 */

if (navigator.userAgent.indexOf("MSIE") > -1) {
    var _VC_IsIE = true;
} else {
    var _VC_IsIE = false;
}
var _VC_ClickStatRate = 10;
if (screen.width >= 1024) {
    var _VC_DisplayBroadPage = true;
} else {
    var _VC_DisplayBroadPage = false;
}


function getCookieVal(offset) {
	var endstr = document.cookie.indexOf (";", offset);
	if (endstr == -1) {
		endstr = document.cookie.length;
	}
	return unescape(document.cookie.substring(offset, endstr));
}
function getCookie(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg)
			return getCookieVal (j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0) break; 
	}
	return null;
}
function setCookie(name, value) {
	var argv = setCookie.arguments;
	var argc = setCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	document.cookie = name + "=" + escape (value) +
		((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
		((path == null) ? "" : ("; path=" + path)) +
		((domain == null) ? "" : ("; domain=" + domain)) +
		((secure == true) ? "; secure" : "");
}

function showElement(strtype)
{
	document.getElementById(strtype).style.display="block";
	document.getElementById("Img_"+strtype).style.display="block";
}
function hideElement(strtype)
{
	document.getElementById(strtype).style.display="none";
	document.getElementById("Img_"+strtype).style.display="none";
}

function innerText(o)
{
	if (document.all) {
		return o.innerText;
	} else {
		return o.textContent;
	}
}

function getRadioValue(fieldName)
{
  var field = document.getElementsByName(fieldName);
  if (field == undefined)
    field = document.getElementById(fieldName);

  var keyValue;
  var isChecked=-1;

  for (loop=0; loop<field.length; loop++)
  {
      if (field[loop].checked)
      {
          isChecked=loop;
          break; // only one needs to be checked
      }
  }
  
  if (isChecked == -1)
  {
    return undefined;
  }

  keyValue = field[isChecked].value;
  return keyValue;
}

function GtWindowOpen(Source, WindowName, wHeight, wWidth){
	scrnW = screen.width;
	scrnH = screen.height;

	t = (scrnH - wHeight) /2;
	l = (scrnW - wWidth) /2;
	try{
		windowParam = "";
		windowParam += "toolbar=no,menubar=no,scrollbars=yes,status=yes,location=no";
		windowParam += ",directories=no,copyhistory=no,resizable=yes";
		windowParam += ",height=" + wHeight + ",width=" + wWidth + ",left=" + l + ",top=" + t;
	
		SubWin = window.open(Source , WindowName, windowParam );
		SubWin.focus();
	} catch (e) {
		;
	}
}


function PBMOver(obj)
{
	obj.className='PBMouseOver';
}
function PBMOut(obj)
{
	obj.className='PBMouseOut';
}

function isAlphaNumber(val){
   if (val.length == 0) {
       return true;
   }
   for (var i = 0; i < val.length; ++i) {
       DataChar = val.charCodeAt(i);
       if(	!((DataChar >= 48 && DataChar <= 57) ||		//0～9
        	(DataChar >= 65 && DataChar <= 90) ||        //A～Z
        	(DataChar >= 97 && DataChar <= 122)))    	//a～z
       {         
            return false;
       }
   }
   return true;
}

//
//
function textMaxLengthCheck(obj, maxLength)
{
    if (getByteCount(obj.value) > maxLength)
    {
        return false;
    }
    return true;
}

//
//
function getByteCount(val)
{
  var count = 0;
  for ( var i = 0; i < val.length; ++i )
  {
    var sub = val.substring(i, i + 1);
    if(isDoubleByte(sub) )
    {
      count += 2;
    } else
    {
      count += 1;
    }
  }
  return count;
}

//
//
function isDoubleByte(val)
{
  for (var i = 0; i < val.length; ++i)
  {
    var c = val.charCodeAt(i);
    if (c < 256 || (c >= 0xff61 && c <= 0xff9f))
    {
      return false;
    }
  }
  return true;
}

var isSubmitFlg = 0;
function nidoosi() {
	if(isSubmitFlg==0){
		isSubmitFlg=1;
		return true;
	}else{
		alert("重复提交网页.");
		return false;
	}
}

function suitPic() {
    var imageName = "post_img";
    var blockArea = "resoucePostData";
    var srcWidth  = 0;
    var srcHeight = 0;
    var changed   = 0;
    if (_VC_DisplayBroadPage) {
        var setWidth = 760;
    } else {
        var setWidth = 592;
    }
    var setHeight  = 2000;
    var postImages = document.getElementsByName(imageName);
    try {
        for (i = 0; i < postImages.length; i++) {
            var e = postImages[i];
            srcWidth  = e.width;
            srcHeight = e.height;
            changed = 0;
            if (e.width > setWidth) {
                n = e.width / setWidth;
                e.width = setWidth;
                e.height = srcHeight / n;
                changed = 1;
            }
            if (e.height > setHeight) {
                n = e.height / setHeight;
                e.width = e.width / n;
                e.height = setHeight;
                changed = 1;
            }
            if (changed == 1) {
                e.insertAdjacentHTML("AfterEnd", "<div style=\"margin-top:4px;\">原图:<a href=\"" + e.src + "\">" + e.src + "</a> | " + srcWidth + "x" + srcHeight + "</div>");
            }
        }
        window.setTimeout("suitPic()", 1000);
    }
    catch(e) {
        window.setTimeout("suitPic()", 1000);
    }
}
suitPic();

/**
 * FreeIPB eMule下载时使用
 */
function checkAll(str,checked) {
    var a = document.getElementsByName(str);
    var n = a.length;

    for (var i = 0; i < n; i++) {
        a[i].checked = checked;
    }
    em_size(str);
}

function download(str, i, first) {
    var a = document.getElementsByName(str);
    var n = a.length;

    for (var i = i; i < n; i++) {
        if(a[i].checked) {
            window.location=a[i].value;
            if (first)
                timeout = 6000;
            else
                timeout = 500;
            i++;
            window.setTimeout("download('"+str+"', "+i+", 0)", timeout);
            break;
        }
    }
}

function copy(str) {
    
    var a = document.getElementsByName(str);
    var n = a.length;
    var ed2kcopy = "";
    for (var i = 0; i < n; i++) {
        if(a[i].checked) {
            ed2kcopy += a[i].value;
            ed2kcopy += "\r\n";
        }
    }
    copyToClipboard(ed2kcopy);
}

function copyToClipboard(txt) {
	if(window.clipboardData) {
   		window.clipboardData.clearData();
   		window.clipboardData.setData("Text", txt);
	} else if(navigator.userAgent.indexOf("Opera") != -1) {
		window.location = txt;
	} else if (window.netscape) {
		try {
			netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
		} catch (e) {
			alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
		}
		var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
		if (!clip)
			return;
		var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
		if (!trans)
			return;
		trans.addDataFlavor('text/unicode');
		var str = new Object();
		var len = new Object();
		var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
		var copytext = txt;
		str.data = copytext;
		trans.setTransferData("text/unicode",str,copytext.length*2);
		var clipid = Components.interfaces.nsIClipboard;
		if (!clip)
			return false;
		clip.setData(trans,null,clipid.kGlobalClipboard);
	}
}

function em_size(str) {
    var a = document.getElementsByName(str);
    var n = a.length;
    try {
        var input_checkall = document.getElementById("checkall_"+str);
        var size = 0;
        input_checkall.checked = true ;
        for (var i=0; i < n; i++) {
            if (a[i].checked) {
                var piecesArray = a[i].value.split( "|" );
                size += piecesArray[3]*1;
            } else {
                input_checkall.checked = false;
            }
        }
        test = document.getElementById("size_"+str);
        test.innerHTML = gen_size(size, 3, 1);
    } catch (e) {

    }
}

function gen_size(val, li, sepa ) {
    sep = Math.pow(10, sepa); //小数点后的位数
    li = Math.pow(10, li); //开始截断的长度
    retval  = val;
    unit    = 'Bytes';
    if (val >= li*1000000000) {
        val = Math.round( val / (1099511627776/sep) ) / sep;
        unit  = 'TB';
    } else if (val >= li*1000000) {
        val = Math.round( val / (1073741824/sep) ) / sep;
        unit  = 'GB';
    } else if (val >= li*1000) {
        val = Math.round( val / (1048576/sep) ) / sep;
        unit  = 'MB';
    } else if (val >= li) {
        val = Math.round( val / (1024/sep) ) / sep;
        unit  = 'KB';
    }
    return val + unit;
}

function utf8_encode(string) {
   return encodeURI(string).replace(/\+/g,"%2B").replace(/(%20)+/, "+");
}

function IsInstallSupMiniQQ(QQVersion) {
	if (_VC_IsIE) {
		try { //支持			
			var xmlhttp = new ActiveXObject("TimwpDll.TimwpCheck");
			var  n = xmlhttp.GetVersion();
			if (n < QQVersion) {
				location.href = 'http://is.qq.com/up_alarm.shtml';
				return false;
			}
		    return true;
		} catch(e) { //不支持
			location.href = 'http://is.qq.com/up_alarm.shtml';
			return false;
		}
		return false;
	} else {
		alert('抱歉，由于腾讯比较傻，这个功能只能在IE下使用:(');
	}
}

function RealAddPortal(FpanelID, QQVersion) {
	var boolFals = IsInstallSupMiniQQ(QQVersion);
	if(boolFals == true) {
		if(FpanelID == 17002) {
			var exe = "Tencent://AddPortal/?Menu=Yes&Exe=QQ&PanelID=" + FpanelID;
		} else {
			var exe = "Tencent://AddPortal/?Menu=Yes&PanelID=" + FpanelID;
		}
		location.href = exe;
	}
}

function setLastVisitCookie(age) {
	var rightNow = new Date();
	var expdate = new Date();
	rightNow.setTime (rightNow.getTime() + (age * 1000)); 
	expdate.setTime (expdate.getTime() + 7 * (24 * 60 * 60 * 1000)); //+7 day
	setCookie ("Exp2", rightNow.getTime(), expdate, "/", ".opentravelsoft.com");
}

function resetCookie() {
	setCookie("Exp2", 0, null, "/");
}

var thisLocation = document.location + "";
var Exp = getCookie("Exp2");
