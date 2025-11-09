import { registerPlugin } from '@capacitor/core';
const DirectoryPicker = registerPlugin('DirectoryPicker', {
    web: () => import('./web').then((m) => new m.DirectoryPickerWeb()),
});
export * from './definitions';
export { DirectoryPicker };
//# sourceMappingURL=index.js.map