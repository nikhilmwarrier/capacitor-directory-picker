export interface FileInfo {
  name: string;
  uri: string;
  type: string;
  size: number;
  lastModified: number;
}

export interface DirectoryPickerPlugin {
  pickDirectory(): Promise<{ uri: string }>;
  readFilesFromDirectory(options: { uri: string }): Promise<{ files: FileInfo[] }>;
  copy(options: { from: string; to: string }): Promise<{ uri: string; name: string }>;
}
