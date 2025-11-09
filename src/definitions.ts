export interface FileInfo {
  name: string;
  uri: string;
  type: string;
  size: number;
  lastModified: number;
}

export interface DirectoryPickerPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  pickDirectory(): Promise<{ uri: string }>;
  readFilesFromDirectory(options: { uri: string }): Promise<{ files: FileInfo[] }>;
}
