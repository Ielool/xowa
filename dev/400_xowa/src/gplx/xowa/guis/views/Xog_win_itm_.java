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
package gplx.xowa.guis.views;

import cn.edu.ruc.xowa.log.action.*;
import gplx.Keyval_;
import gplx.String_;
import gplx.gfui.controls.elems.GfuiElem;
import gplx.gfui.controls.standards.GfuiBtn;
import gplx.gfui.controls.standards.GfuiComboBox;
import gplx.gfui.controls.standards.GfuiTextBox;
import gplx.gfui.controls.standards.Gfui_grp;
import gplx.gfui.controls.windows.GfuiWin;
import gplx.gfui.draws.FontAdp;
import gplx.gfui.imgs.IconAdp;
import gplx.gfui.kits.core.Gfui_kit;
import gplx.xowa.Xoae_app;
import gplx.xowa.guis.langs.Xol_font_info;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

import java.util.List;
import java.util.UUID;

public class Xog_win_itm_ {

	private static UUID uuid;
	private static String UserName;

	public static void PopupLoginForm() {
		Shell shell = Display.getDefault().getActiveShell();
		InputDialog input = new InputDialog(shell,
		"User Registration", "Please input your name here:",
		"",null);
		if(input.open()== Window.OK)
		{
			String userName = input.getValue();
			UserName = userName;
			Action loginAction = new LoginAction(userName);
			loginAction.perform();
			//System.out.println(input.getValue());
		}

		Shell choiceshell = new Shell();
		Text text = new Text(choiceshell, SWT.SINGLE | SWT.READ_ONLY);
		text.setText("Step 1. Please choose a module that you wanna enjoy.");

		Button buttonTaskGenerate = new Button(choiceshell, SWT.MULTI | SWT.WRAP | SWT.TOGGLE);
		//buttonTaskGenerate.setBounds(10, 5, 100, 30);
		buttonTaskGenerate.setData("Generate a task", null);
		buttonTaskGenerate.setBackground(choiceshell.getBackground());
		buttonTaskGenerate.setText("Generate a task");

		Button buttonTaskHandle = new Button(choiceshell, SWT.MULTI | SWT.WRAP | SWT.TOGGLE);
		//buttonTaskHandle.setBounds(100, 5, 80, 30);
		buttonTaskHandle.setData("Finish a task", null);
		buttonTaskHandle.setBackground(choiceshell.getBackground());
		buttonTaskHandle.setText("Finish a task");

		Button buttonReset = new Button(choiceshell, SWT.MULTI | SWT.WRAP | SWT.TOGGLE);
		buttonReset.setData("Reset", null);
		buttonReset.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_RED));
		buttonReset.setText("Reset");

		buttonTaskGenerate.addListener(SWT.Selection, event ->
		{
		    TaskGenerating(choiceshell, buttonTaskGenerate);
		    buttonTaskGenerate.setEnabled(false);
		});
		buttonTaskHandle.addListener(SWT.Selection, event ->
		{
		    TaskHandling(choiceshell, buttonTaskGenerate);
		    buttonTaskHandle.setEnabled(false);
		});
		buttonReset.addListener(SWT.Selection, event -> {
		    buttonTaskGenerate.setEnabled(true);
		    buttonTaskHandle.setEnabled(true);
            choiceshell.setSize(400, 480);
            choiceshell.layout();
            choiceshell.redraw();
        });

		choiceshell.setText("Module Controller");
		FormLayout layout = new FormLayout();
		layout.spacing = 6;
		layout.marginHeight = layout.marginWidth = 9;
		choiceshell.setLayout(layout);

		FormData textData = new FormData();
		textData.top = new FormAttachment(0);
		textData.left = new FormAttachment(0);
		textData.right = new FormAttachment(90);
		text.setLayoutData(textData);

		FormData buttonTaskGenerateData = new FormData();
		buttonTaskGenerateData.top = new FormAttachment(text);
		buttonTaskGenerateData.left = new FormAttachment(0);
		buttonTaskGenerateData.right = new FormAttachment(40);
		buttonTaskGenerate.setLayoutData(buttonTaskGenerateData);

		FormData buttonTaskHandleData = new FormData();
		buttonTaskHandleData.top = new FormAttachment(text);
		buttonTaskHandleData.left = new FormAttachment(buttonTaskGenerate);
		buttonTaskHandle.setLayoutData(buttonTaskHandleData);

		FormData buttonResetData = new FormData();
		buttonResetData.top = new FormAttachment(text);
        buttonResetData.left = new FormAttachment(buttonTaskHandle);
        buttonReset.setLayoutData(buttonResetData);

		/*GridLayout choicelayout = new GridLayout();
		choicelayout.numColumns = 2;
		choiceshell.setLayout(choicelayout);*/
		//Rectangle rectangle = Display.getCurrent().getPrimaryMonitor().getClientArea();
		//popupshell.setLocation((rectangle.width - 286) / 2 + 300, 0);
		//choiceshell.setMenuBar(new Menu(choiceshell, SWT.BAR));
		choiceshell.setSize(400, 480);
		choiceshell.open();
	}
	public static void TaskGenerating(Shell choiceshell, Button button){
		Text text2 = new Text(choiceshell, SWT.WRAP | SWT.READ_ONLY);
		text2.setText("Step 2. You choose the task generation module. Please select a phrase below.");
		FormData textData = new FormData();
		textData.top = new FormAttachment(button);
		textData.left = new FormAttachment(0);
		textData.right = new FormAttachment(90);
		text2.setLayoutData(textData);

		final Combo combo = new Combo(choiceshell, SWT.DROP_DOWN | SWT.READ_ONLY);
		String[] Items = {"-----------","in other words", "in plain English", "that is to say", "namely"};
		combo.setItems(Items);
		combo.select(0);
		//combo.setBounds(10,80, 200,50);
		FormData comboData = new FormData();
		comboData.top = new FormAttachment(text2);
		comboData.left = new FormAttachment(0);
		comboData.right = new FormAttachment(90);
		combo.setLayoutData(comboData);

		combo.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                List<String> entityResults = null;
                if (combo.getText().equals("in other words"))
                {
                    //pass "in other words" to BG and return back entity names
                    String keyword = "in other words";
                    Action returnEntities= new ReturnEntitiesAction(keyword);
                    entityResults = returnEntities.get();
                }else if (combo.getText().equals("in plain English"))
                {
                    //pass "in plain English" to BG and return back entity names
                    String keyword = "in plain English";
                    Action returnEntities = new ReturnEntitiesAction(keyword);
                    entityResults = returnEntities.get();
                }else if (combo.getText().equals("that is to say"))
                {
                    //pass "that is to say" to BG and return back entity names
                    String keyword = "that is to say";
                    Action returnEntities = new ReturnEntitiesAction(keyword);
                    entityResults = returnEntities.get();
                }else if (combo.getText().equals("namely"))
                {
                    //pass "namely" to BG and return back entity names
                    String keyword = "namely";
                    Action returnEntities = new ReturnEntitiesAction(keyword);
                    entityResults = returnEntities.get();
                }
                Text text3 = new Text(choiceshell, SWT.WRAP | SWT.READ_ONLY);
				text3.setText("Step 3. Wikipedia pages of the following entities" +
						"have the selected phrase, please choose one and get the corresponding " +
						"entity page in XOWA.");
				FormData text3Data = new FormData();
				text3Data.top = new FormAttachment(combo);
				text3Data.left = new FormAttachment(0);
				text3Data.right = new FormAttachment(90);
				text3.setLayoutData(text3Data);

				ScrolledComposite scrolledComposite = new ScrolledComposite(choiceshell, SWT.BORDER | SWT.V_SCROLL);
				//scrolledComposite.setBounds();
				Composite composite = new Composite(scrolledComposite, SWT.NONE);
				composite.setSize(326, 600);
                scrolledComposite.setContent(composite);

                String entityNames = new String();
                for (String entity : entityResults){
                	if (entity.length() > 15)
					{
						entityNames += entity +"\n";
					}
				}
                Text entities = new Text(composite, SWT.BORDER);
                entities.setText(entityNames);

                FormData scrolledCompositeData = new FormData();
                scrolledCompositeData.top = new FormAttachment(text3);
                scrolledCompositeData.left = new FormAttachment(0);
                scrolledCompositeData.right = new FormAttachment(90);
                scrolledCompositeData.height = 200;
                scrolledComposite.setLayoutData(scrolledCompositeData);
                choiceshell.layout();
                choiceshell.redraw();
            }
        });
		choiceshell.layout();
		choiceshell.redraw();
	}
	public static void TaskHandling(Shell choiceshell, Button button){
        Text text2 = new Text(choiceshell, SWT.WRAP | SWT.READ_ONLY);
        text2.setText("Step 2. You choose to handle a task.\nClick on \"Begin\" to start search. \n" +
                "Click on \"Finish\" to finish you explorations.\n" +
                "Click on \"Give up\" when you wanna give up.");
        FormData textData = new FormData();
        textData.top = new FormAttachment(button);
        textData.left = new FormAttachment(0);
        textData.right = new FormAttachment(90);
        text2.setLayoutData(textData);

		Button buttonStart = new Button(choiceshell, SWT.MULTI | SWT.WRAP | SWT.TOGGLE);
		//buttonStart.setBounds(10, 5, 80, 30);
		buttonStart.setData("Begin", null);
		buttonStart.setBackground(choiceshell.getBackground());
		buttonStart.setText("Begin");

		Button buttonEnd = new Button(choiceshell, SWT.MULTI | SWT.WRAP | SWT.TOGGLE);
		//buttonEnd.setBounds(100, 5, 80, 30);
		buttonEnd.setData("Finish", null);
		buttonEnd.setBackground(choiceshell.getBackground());
		buttonEnd.setText("Finish");
		buttonEnd.setEnabled(false);

		Button buttonGiveUp = new Button(choiceshell, SWT.MULTI | SWT.WRAP | SWT.TOGGLE);
		//buttonGiveUp.setBounds(190, 5, 80, 30);
		buttonGiveUp.setData("Give Up", null);
		buttonGiveUp.setBackground(choiceshell.getBackground());
		buttonGiveUp.setText("Give Up");
		buttonGiveUp.setEnabled(false);
		/*
		Button buttonDrawGraph = new Button(popupshell, SWT.MULTI | SWT.WRAP);
		buttonDrawGraph.setBounds(280, 5, 80, 30);
		buttonDrawGraph.setData("Review Navigation", null);
		buttonDrawGraph.setBackground(popupshell.getBackground());
		buttonDrawGraph.setText("Review");
		buttonDrawGraph.setEnabled(false);*/

		buttonStart.addListener(SWT.Selection, event -> {
			//System.out.println("start clicked...");
			//UUID uuid = UUID.randomUUID();
			uuid = UUID.randomUUID();
			Action startAction = new StartSessionAction(uuid.toString());
			startAction.perform();
			buttonStart.setEnabled(false);
			buttonEnd.setEnabled(true);
			buttonGiveUp.setEnabled(true);
			Text text3 = new Text(choiceshell, SWT.WRAP | SWT.READ_ONLY);
			text3.setText("Step 3. Start exploration.......\n"+"User Name: "+ UserName);
            FormData txtData = new FormData();
            txtData.top = new FormAttachment(buttonStart);
            txtData.left = new FormAttachment(0);
            txtData.right = new FormAttachment(90);
            text3.setLayoutData(txtData);
            choiceshell.layout();
            choiceshell.redraw();
		});

		buttonEnd.addListener(SWT.Selection, event -> {
			//System.out.println("finish clicked...");
			Action endAction = new EndSessionAction();
			endAction.perform();
			buttonStart.setEnabled(true);
			buttonEnd.setEnabled(false);
			buttonGiveUp.setEnabled(false);
			//buttonDrawGraph.setEnabled(true);
		});

		buttonGiveUp.addListener(SWT.Selection, event -> {
			//System.out.println("give up clicked...");
			Action giveupAction = new GiveupSessionAction();
			giveupAction.perform();
			buttonStart.setEnabled(true);
			buttonEnd.setEnabled(false);
			buttonGiveUp.setEnabled(false);
			//buttonDrawGraph.setEnabled(true);
		});

		/*
		buttonDrawGraph.addListener(SWT.Selection, event -> {
			Canvas canvas = new Canvas(popupshell, SWT.NONE);
			canvas.setSize(280, 280);
			canvas.setLocation(6,20);
			Action drawpathAction = new DrawpathAction(uuid.toString(), canvas);
			drawpathAction.perform();
			popupshell.setSize(286, 300);
		});*/
        FormData buttonStartData = new FormData();
        buttonStartData.top = new FormAttachment(text2);
        buttonStartData.left = new FormAttachment(0);
        buttonStartData.right = new FormAttachment(30);
        buttonStart.setLayoutData(buttonStartData);

        FormData buttonEndData = new FormData();
        buttonEndData.top = new FormAttachment(text2);
        buttonEndData.left = new FormAttachment(buttonStart);
        buttonEnd.setLayoutData(buttonEndData);

        FormData buttonGiveUpData = new FormData();
        buttonGiveUpData.top = new FormAttachment(text2);
        buttonGiveUpData.left = new FormAttachment(buttonEnd);
        buttonGiveUp.setLayoutData(buttonGiveUpData);

        choiceshell.layout();
        choiceshell.redraw();
	}

	public static void Show_win(Xog_win_itm win) {
		PopupLoginForm();
		Xoae_app app = win.App();
		GfuiWin win_box = win.Win_box();
		win_box.Focus_able_(false);
		app.Gui_mgr().Nightmode_mgr().Enabled_by_cfg();
		Xog_startup_win_.Startup(app, win_box);

		win_box.Icon_(IconAdp.file_or_blank(app.Fsys_mgr().Bin_xowa_dir().GenSubFil_nest("file", "app.window", "app_icon.png")));
	}

	public static void Show_widget(boolean show, GfuiElem box, GfuiElem btn) {
		int box_w, btn_w;
		if (show) {
			box_w = Toolbar_txt_w;
			btn_w = Toolbar_btn_w;
		}
		else {
			box_w = 0;
			btn_w = 0;
		}
		box.Layout_data_(new gplx.gfui.layouts.swts.Swt_layout_data__grid().Hint_w_(box_w).Hint_h_(Toolbar_grp_h));	// WORKAROUND.SWT: need to specify height, else SWT will shrink textbox on re-layout when showing / hiding search / allpages; DATE:2017-03-28
		btn.Layout_data_(new gplx.gfui.layouts.swts.Swt_layout_data__grid().Hint_w_(btn_w).Align_w__fill_());
	}
	public static Gfui_grp new_grp(Xoae_app app, Gfui_kit kit, GfuiElem win, String id) {
		return kit.New_grp(id, win);
	}
	public static GfuiBtn new_btn(Xoae_app app, Gfui_kit kit, GfuiElem win, String id) {
		return kit.New_btn(id, win);
	}
	public static GfuiComboBox new_cbo(Xoae_app app, Gfui_kit kit, GfuiElem win, FontAdp ui_font, String id, boolean border_on) {
		GfuiComboBox rv = kit.New_combo(id, win, Keyval_.new_(GfuiTextBox.CFG_border_on_, border_on));
		rv.TextMgr().Font_(ui_font);
		return rv;
	}
	public static GfuiTextBox new_txt(Xoae_app app, Gfui_kit kit, GfuiElem win, FontAdp ui_font, String id, boolean border_on) {
		GfuiTextBox rv = kit.New_text_box(id, win, Keyval_.new_(GfuiTextBox.CFG_border_on_, border_on));
		rv.TextMgr().Font_(ui_font);
		return rv;
	}
	public static void Update_tiptext(Xoae_app app, GfuiElem elem, int tiptext_id) {
		elem.TipText_(Xog_win_itm_.new_tiptext(app, tiptext_id));
	}
	public static void Font_update(Xog_win_itm win, Xol_font_info itm_font) {
		FontAdp gui_font = win.Url_box().TextMgr().Font();
		if (!itm_font.Eq(gui_font)) {
			FontAdp new_font = itm_font.To_font();
			win.Url_box().TextMgr().Font_(new_font);
			win.Search_box().TextMgr().Font_(new_font);
			win.Allpages_box().TextMgr().Font_(new_font);
			win.Find_box().TextMgr().Font_(new_font);
			win.Prog_box().TextMgr().Font_(new_font);
			win.Info_box().TextMgr().Font_(new_font);
			win.Tab_mgr().Tab_mgr().TextMgr().Font_(new_font);
		}
	}
	public static String new_tiptext(Xoae_app app, int id) {return String_.new_u8(app.Usere().Lang().Msg_mgr().Val_by_id(app.Usere().Wiki(), id));}
	public static final int 
	  Toolbar_grp_h = 24
	, Toolbar_txt_w = 160
	, Toolbar_btn_w = 16;
}
