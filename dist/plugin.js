var capacitorDirectoryPicker = (function (exports, core) {
    'use strict';

    const DirectoryPicker = core.registerPlugin('DirectoryPicker', {
        web: () => Promise.resolve().then(function () { return web; }).then((m) => new m.DirectoryPickerWeb()),
    });

    class DirectoryPickerWeb extends core.WebPlugin {
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

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        DirectoryPickerWeb: DirectoryPickerWeb
    });

    exports.DirectoryPicker = DirectoryPicker;

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
