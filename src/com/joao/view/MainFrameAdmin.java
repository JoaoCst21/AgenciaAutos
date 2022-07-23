package com.joao.view;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import java.util.ArrayList;

public class MainFrameAdmin extends MainFrame{
    public MainFrameAdmin() {
        super(new String[] {"create", "read", "update", "delete"});
    }

    @Override
    protected void displayFrames(Object item) {
        ArrayList<ArrayList<JMenuItem>> menuItems = new ArrayList<>() {{
            add(carMenuItems);
            add(motorcycleMenuItems);
            add(busMenuItems);
            add(userMenuItems);
        }};

        methods = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new CreateFrameCar());
                add(new ReadFrameCar());
                add(new UpdateFrameCar());
                //
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameMotorcycle());
                add(new ReadFrameMotorcycle());
                add(new UpdateFrameMoto());
                //
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameBus());
                add(new ReadFrameBus());
                add(new UpdateFrameBus());
                //
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameUserAdmin());
                add(new ReadFrameUser());
                add(new UpdateFrameUser());
                //
            }});
        }};

        int i = 0;
        for (ArrayList<JMenuItem> menuItem : menuItems) {
            for (int ind = 0; ind < menuItem.size(); ind++) {
                if (item == menuItem.get(ind)) {
                    // desactiveFrame
                    if (activeFrame != null) activeFrame.dispose();

                    // declare ActiveFrame
                    activeFrame = methods.get(i).get(ind);

                    // add activeFrame
                    add(activeFrame);
                    return;
                }
            }
            i++;
        }
    }
}
