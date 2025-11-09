import { WebPlugin } from '@capacitor/core';
import type { DirectoryPickerPlugin, FileInfo } from './definitions';
export declare class DirectoryPickerWeb extends WebPlugin implements DirectoryPickerPlugin {
    pickDirectory(): Promise<{
        uri: string;
    }>;
    readFilesFromDirectory(options: {
        uri: string;
    }): Promise<{
        files: FileInfo[];
    }>;
    copy(options: {
        from: string;
        to: string;
    }): Promise<{
        uri: string;
        name: string;
    }>;
}
