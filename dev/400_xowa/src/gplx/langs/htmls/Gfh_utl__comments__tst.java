/*
XOWA: the XOWA Offline Wiki Application
Copyright (C) 2012-2017 gnosygnu@gmail.com

XOWA is licensed under the terms of the General Public License (GPL) Version 3,
or alternatively under the terms of the Apache License Version 2.0.

You may use XOWA according to either of these licenses as is most appropriate
for your project on a case-by-case basis.

The terms of each license can be found in the source code repository:

GPLv3 License: https://github.com/gnosygnu/xowa/blob/master/LICENSE-GPLv3.txt
Apache License: https://github.com/gnosygnu/xowa/blob/master/LICENSE-APACHE2.txt
*/
package gplx.langs.htmls; import gplx.*; import gplx.langs.*;
import org.junit.*;
public class Gfh_utl__comments__tst {
	@Before public void init() {fxt.Clear();} private final    Gfh_class_fxt fxt = new Gfh_class_fxt();
	@Test   public void Basic() 		{fxt.Test_del_comments("a<!-- b -->c"				, "ac");}
	@Test   public void Bgn_missing() 	{fxt.Test_del_comments("a b c"						, "a b c");}
	@Test   public void End_missing() 	{fxt.Test_del_comments("a<!-- b c"					, "a");}
	@Test   public void Multiple()	 	{fxt.Test_del_comments("a<!--b-->c<!--d-->e"		, "ace");}
}