# @nikhilmwarrier/capacitor-directory-picker

Uses SAF to pick a directory, and returns the files inside it.

## Install

```bash
npm install @nikhilmwarrier/capacitor-directory-picker
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`pickDirectory()`](#pickdirectory)
* [`readFilesFromDirectory(...)`](#readfilesfromdirectory)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => any
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>any</code>

--------------------


### pickDirectory()

```typescript
pickDirectory() => any
```

**Returns:** <code>any</code>

--------------------


### readFilesFromDirectory(...)

```typescript
readFilesFromDirectory(options: { uri: string; }) => any
```

| Param         | Type                          |
| ------------- | ----------------------------- |
| **`options`** | <code>{ uri: string; }</code> |

**Returns:** <code>any</code>

--------------------


### Interfaces


#### FileInfo

| Prop               | Type                |
| ------------------ | ------------------- |
| **`name`**         | <code>string</code> |
| **`uri`**          | <code>string</code> |
| **`type`**         | <code>string</code> |
| **`size`**         | <code>number</code> |
| **`lastModified`** | <code>number</code> |

</docgen-api>
