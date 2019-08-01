
import { NativeModules ,requireNativeComponent} from 'react-native';

const { RNReactNativeFtPdf } = NativeModules;

module.exports = {JRNPdfView:requireNativeComponent('JRNPdfView'),RNReactNativeFtPdf};
