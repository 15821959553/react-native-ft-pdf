
# react-native-ft-pdf

## Getting started

`$ npm install react-native-ft-pdf --save`

### Mostly automatic installation

`$ react-native link react-native-ft-pdf`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-ft-pdf` and add `RNReactNativeFtPdf.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNReactNativeFtPdf.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNReactNativeFtPdfPackage;` to the imports at the top of the file
  - Add `new RNReactNativeFtPdfPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:

  	include ':react-native-ft-pdf'
  	project(':react-native-ft-pdf').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-ft-pdf/android')

3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:

      compile project(':react-native-ft-pdf')

ATTENTION：

if  you want the RNAndroidPdfView  work ,please  add

    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
into  Mainfest............

Add this at the build.gradle：

repositories {
... ...
maven { url 'https://www.jitpack.io' }
}


At  last :  if an error occurred liked "Mainfest  merge  failed" ,you  need  add some things like down:
add   xmlns:tools="http://schemas.android.com/tools"  in  <mainfest  />
add   tools:replace="android:allowBackup"  in  <application  />



## Usage

import {JRNPdfView} from 'react-native-ft-pdf';


URI:  you need send the PDF file's name

<JRNPdfView uri={"moby.pdf"}  style={{width:Dimensions.get('window').width,height:Dimensions.get('window').height}}/>

URL:默认水平方向滑动翻页 ，如果你想垂直方向滑动翻页 使用 vurl属性

<JRNPdfView url={"url"}  style={{width:Dimensions.get('window').width,height:Dimensions.get('window').height}}/>


if you want to get the PDF from intent  and  get the complete state ,

Usage :

componentDidMount() {
this.listener = null;
if (Platform.OS === 'ios') {
//todo
} else {
this.listener = DeviceEventEmitter.addListener("complete", (result) => {
this.showAlert("获取到加载结束的通知" + result);   //result  is a boolean : true
});
}
}
  
