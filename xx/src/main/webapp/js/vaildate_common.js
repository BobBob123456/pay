function checkBankCardNo(e) {
	var o = "10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";
	if (o.indexOf(e.substring(0, 2)) == -1) {
		return false
	}
	var y = e.substr(e.length - 1, 1);
	var b = e.substr(0, e.length - 1);
	var B = new Array();
	for (var x = b.length - 1; x > -1; x--) {
		B.push(b.substr(x, 1))
	}
	var t = new Array();
	var a = new Array();
	var g = new Array();
	for (var w = 0; w < B.length; w++) {
		if ((w + 1) % 2 == 1) {
			if (parseInt(B[w]) * 2 < 9) {
				t.push(parseInt(B[w]) * 2)
			} else {
				a.push(parseInt(B[w]) * 2)
			}
		} else {
			g.push(B[w])
		}
	}
	var d = new Array();
	var c = new Array();
	for (var z = 0; z < a.length; z++) {
		d.push(parseInt(a[z]) % 10);
		c.push(parseInt(a[z]) / 10)
	}
	var A = 0;
	var v = 0;
	var l = 0;
	var f = 0;
	var D = 0;
	for (var s = 0; s < t.length; s++) {
		A = A + parseInt(t[s])
	}
	for (var r = 0; r < g.length; r++) {
		v = v + parseInt(g[r])
	}
	for (var q = 0; q < d.length; q++) {
		l = l + parseInt(d[q]);
		f = f + parseInt(c[q])
	}
	D = parseInt(A) + parseInt(v) + parseInt(l) + parseInt(f);
	var u = parseInt(D) % 10 == 0 ? 10 : parseInt(D) % 10;
	var C = 10 - u;
	if (y == C) {
		return true
	} else {
		return false
	}
}