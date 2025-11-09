import { WebPlugin } from '@capacitor/core';
export class DirectoryPickerWeb extends WebPlugin {
    async pickDirectory() {
        throw this.unimplemented('Not implemented on web.');
    }
    async readFilesFromDirectory(options) {
        console.log(options);
        throw this.unimplemented('Not implemented on web.');
    }
    async copy(options) {
        console.log(options);
        throw this.unimplemented('Not implemented on web.');
    }
}
//# sourceMappingURL=web.js.map