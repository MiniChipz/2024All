use std::cell::RefCell;
use std::rc::Rc;

#[cfg_attr(not(debug_assertions), windows_subsystem = "windows")]

slint::slint!{
    import { Button, VerticalBox } from "std-widgets.slint";
    
    export global calcLogic {
        callback button-pressed(string);
    }

    component Button {
        min-height: 30px;
        min-width: 30px;

        in property <string> text;
        Rectangle {
            background: ta.pressed ? grey : ta.has-hover ? #42414468 : #ffffff60;
            border-radius: 4px;
            border-width: 2px;
            border-color: self.background.darker(20%);
            ta := TouchArea { 
               clicked => { calcLogic.button-pressed(root.text); }
            }
        }
        Text { text: root.text; }
        
    }

    export component App inherits Window {
        min-width: 200px;
        min-height: 150px;
        max-width: 800px;
        max-height: 600px;

        in property <int> Value: 0;
        always-on-top: true;

        GridLayout {
            padding: 10px;
            spacing: 5px;
            Text { text: Value; colspan: 3; col: 1;}
            Row {
                Button { text: "1"; width: 35px; }
                Button { text: "2"; width: 35px;  }
                Button { text: "3"; width: 35px;  }
                Button { text: "+"; width: 35px; }
            }
            Row {
                Button { text: "4"; }
                Button { text: "5"; }
                Button { text: "6"; }
                Button { text: "-"; width: 35px; }

            }
            Row {
                Button { text: "7"; }
                Button { text: "8"; }
                Button { text: "9"; }
                Button { text: "*"; width: 35px; }

            }
            Row {
                Button { text: "0"; col: 1; }
                Button { text: "/"; col: 3;}
                Button { text: "="; col: 2; }
            }
        }
    }
}
#[derive(Default)]
struct CalcState {
    prev_value: i32,
    current_value: i32,
    operator: slint::SharedString,
}

fn main() {
    let app = App::new().unwrap();
    let weak = app.as_weak();
    let state = Rc::new(RefCell::new(CalcState::default()));
    app.global ::<calcLogic>().on_button_pressed(move |value| {
        let app = weak.unwrap();
        let mut state = state.borrow_mut();
        if let Ok(val) = value.parse::<i32>() {
<<<<<<< HEAD
            state.current_value = val;
=======
            state.current();
>>>>>>> a222566b6e7532c72c208859ab4ebf56db71ef32
        app.set_Value(app.get_Value() * 10 + val);
        return
    }
    });

    app.run().unwrap();
}
