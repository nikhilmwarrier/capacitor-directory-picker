import { WebPlugin } from '@capacitor/core';

import type { DirectoryPickerPlugin, FileInfo } from './definitions';

export class DirectoryPickerWeb extends WebPlugin implements DirectoryPickerPlugin {
  async pickDirectory(): Promise<{ uri: string }> {
    throw this.unimplemented('Not implemented on web.');
  }

  async readFilesFromDirectory(options: { uri: string }): Promise<{ files: FileInfo[] }> {
    console.log(options);
    throw this.unimplemented('Not implemented on web.');
  }

  async copy(options: { from: string; to: string }): Promise<{ uri: string; name: string }> {
    console.log(options);
    throw this.unimplemented('Not implemented on web.');
  }
}
