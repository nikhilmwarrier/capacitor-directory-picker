# @nikhilmwarrier/capacitor-directory-picker

Uses SAF to pick a directory, and returns the files inside it.

## Install

```bash
npm install @nikhilmwarrier/capacitor-directory-picker
npx cap sync
```

## API

<docgen-index>

* [`pickDirectory()`](#pickdirectory)
* [`readFilesFromDirectory(...)`](#readfilesfromdirectory)
* [`copy(...)`](#copy)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

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


### copy(...)

```typescript
copy(options: { from: string; to: string; }) => any
```

| Param         | Type                                       |
| ------------- | ------------------------------------------ |
| **`options`** | <code>{ from: string; to: string; }</code> |

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
