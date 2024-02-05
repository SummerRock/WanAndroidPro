package com.common.router;

public class RouterConstants {

    public static final String SETTING_ACTIVITY = "/settings/activity";

    public static final String REACT_ACTIVITY = "/react/activity";

    public static final String REACT_FRAGMENT_ACTIVITY =  "/react_fragment/activity";

    public interface RouterKey {
        String DATA_KEY = "data_key";

        String REACT_MODULE_NAME = "react_module_name";
    }

    public interface ReactPageName {
        String MY_COLLECTION = "my_collection";

        String MY_MESSAGE = "my_message";
    }
}
