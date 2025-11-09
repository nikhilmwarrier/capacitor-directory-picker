export interface DirectoryPickerPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
