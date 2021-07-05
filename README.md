# SegmentedController

SegmentedController is an Android UI library for using customizable RadioGroup with RadioButtons.

<img src="https://github.com/erayozenc/SegmentedController/blob/master/segmented_controller.gif" width="300" height="600" />

## Installation

#### Step 1: Add the JitPack repository to your build.gradle(Project: x) file
```groovy
allprojects {
    repositories {
        ...

        maven { url 'https://jitpack.io' }
    }
}
```
#### Step 2: Add the dependency to your build.gradle(Module: x) file

```groovy
dependencies {
    ...
    //SegmentedController
    implementation 'com.github.erayozenc:SegmentedController:1.0.0'

}
```

## Usage

```xml
<com.erayozenc.lib.SegmentController
        android:id="@+id/segmentedController"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:tab_cornerRadius="22dp"
        app:tab_selectedColor="@color/white"
        app:tab_unselectedColor="@color/light_grey"
        app:tab_strokeWidth="4dp">

        <RadioButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="First"/>

        <RadioButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Second"/>

    </com.erayozenc.lib.SegmentController>
```

#### Attributes
```xml
<attr name="tab_strokeWidth" format="dimension"/>
<attr name="tab_cornerRadius" format="dimension"/>
<attr name="tab_selectedColor" format="color"/>
<attr name="tab_unselectedColor" format="color"/>
```

You can also customize your SegmentedController programmatically.

```kotlin
val segmentedController = findViewById<SegmentController>(R.id.segmentedController)

segmentedController.setSelectedTabColor(Color.WHITE)
segmentedController.setUnselectedTabColor(Color.GRAY)
segmentedController.setCornerRadius(22f)
segmentedController.setStrokeWidth(4f)
```

#### NOTE:
Default values for attributes
```bash
tab_strokeWidth = 2dp
tab_cornerRadius = 4dp
tab_selectedColor = Color.WHITE
tab_unselectedColor = Color.LTGRAY
```
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
