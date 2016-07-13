package org.robotframework.formslibrary.operator;

import org.junit.Assert;
import org.robotframework.formslibrary.chooser.ByClassChooser;
import org.robotframework.formslibrary.util.Logger;
import org.robotframework.formslibrary.util.ObjectUtil;

public class TabOperator extends BaseComponentOperator {

    public TabOperator() {
        super(new ByClassChooser(0, "oracle.ewt.tabBar.TabBar"));
    }

    public void select(String name) {

        int tabCount = (Integer) ObjectUtil.invoke(getSource(), "getItemCount");

        for (int i = 0; i < tabCount; i++) {
            Object tabItem = ObjectUtil.invokeWithIntArg(getSource(), "getItem()", i);
            String label = ObjectUtil.getString(tabItem, "getLabel()");
            Logger.debug("Found tab " + label);
            if (name.equalsIgnoreCase(label)) {
                ObjectUtil.invokeMethodWithTypedArg(tabItem, "setSelected()", "boolean", Boolean.TRUE);
                return;
            }
        }

        Assert.fail("Tab " + name + " not found.");
    }
}