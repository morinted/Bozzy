RTF/CRE and JSON Comparison
==============================

- RTF/CRE spec source: http://www.legalxml.org/workgroups/substantive/transcripts/cre-spec.htm
- Plover's RTF parser: https://github.com/openstenoproject/plover/blob/master/plover/dictionary/rtfcre_dict.py

## RTF Novelties

### Font Tag

RTF has a font tag:

```
{\fonttbl
{\f0\fmodern Courier New;}
}
```

That means we shouldn't "wipe out" extra lines from RTF files.

### Revision

CRE Revision seems to be fixed at 1.00: `{\*\cxrev100}`

### System

Need to include the creator of the RTF. May be good to *not* modify ones that we read in, and customize our own for Bozzy.

`{\*\cxsystem {\i KittyCAT} Rev 3.5 by Feral Software, Inc.}`

### Number Bar

The number bar symbol can appear at either end of the stroke, but not both...

### Transcripts

There are steno transcripts... let's ignore those, because we are dealing with dictionaries.

- `{cxsnum}` Numbers
- Repeats
- Timecodes `{\*\cxt}`, `{\v\cxt}`
- Tape Names `cxtname`

### Text

| Title | RTF | JSON | Alternative Name |
|-----| ----- |------| --------------- |
| Hard space |`\~`| `{^ ^}` or Unicode NBS | Non-breaking space |
| Delete space | `\cxds ing` | `{^ing}` | Plover's Attach |
|  |  `\cxds -to- \cxds` | `{^-to-^}` |  |
|  |  `pre-\cxds` | `{pre^}` |  |
| Force cap | `\cxfc` | `{-|}` |  |
| Force lower case | `\cxfl ` | `{>}` |  |
| Stitching* | `\cxstit M` | Unsupported |  |
| Fingerspelling | `\cxfing M` | `{&M}` | Glue |
| EOS Period | `{\cxp. }` | `{.}` | End of sentence |
| EOS Question Mark | `{\cxp? }` | `{?}` |  |
| EOS Exclam | `{\cxp! }` | `{!}` | Period |
| Colon | `{\cxp: }` | `{:}{-|}` | Note: RTF capitalizes after colon, Plover doesn't. |
| Comma | `{\cxp, }` | `{,}` |  |
| Semicolon | `{\cxp; }` | `{;}` |  |
| Possessive apostrophe | `{\cxp' }` | `{^'}` |  |
| Non-breaking hyphen* | `{\cxp- }` | `{^-^}` |  |
| Slash | `{\cxp/ }` | `{^/^}` |  |
| Smart quote | `{\cxp" }` | No equivalent. |  |
| cxp rule: | `{\cxpanything }` | `^anything^` | Be careful here. |
| Paragraph | `\par\sX` where X is digit | `{#Return}` or `{#Return Return}` | Plover doesn't have "paragraphs" |
| Conflicts | `\cxc` | No equivalent. | Could try to give def1/def2/def3... |
| Escaped newline | `\\r\\n` | `{#Return Return}` |  |

### Conflicts

RTF has conflicts... we can try and handle these, but JSON has no equivalent. Basically, it's a feature of steno software to give you the choice between the "conflicts", for non-realtime:

```
{\*\cxs THAEUR}their
{\*\cxs THAEUR}they're
{\*\cxs THAEUR}there
```

Equivalent to:

```
{\*\cxs THAEUR}{\*\cxconf [{\*\cxc their}|{\*\cxc they're}|{\*\cxc
 there}]}
```

### Translation Options

In the header of the dictionary:

```
{\*\cxtranopt
\resaan\numbar\tagused
}
```

*Copied from spec*

| Word  | Meaning |
| ----- | ----- |
| `\numbar` | number bar is used for numbers |
| `\dragr` | *R and R* should be treated like * |
| `\stitchlower` | Stitch with lower case |
| `\asterpar` | `*/*` used for paragraph |
| `\resaan` | resolve a/an conflict |
| `\resanand` | resolve an/and conflict |
| `\tagused` | tag used entries in dictionary |

We can probably ignore most of these except for `\numbar` and `\asterpar` ?

### Comments

`{\*\cxcomment That's a name}`

No JSON equivalent.

### Delete last stroke

`{\*\cxs *}\cxdstroke`

No JSON equivalent.

### Character sets

Some of `\ansi`, `\pc`, `\pca`, and `\mac`

### Unused Tag

`\cxunused`

Represents that a stroke hasn't been used.

## Other RTF

Magnum was created with Digital CAT 00220. In it, there are dict-entry dates:

- `{\*\cxsvatdictentrydate\yrYYYY\moMM\daDD}`
- `{\*\cxsvatdictentrydate\yr2016\mo12\da30}`
- `{\*\cxsvatdictentrydate\yr1994\mo3\da3}`

Other syntax:

- `\*\cxsvatdictflags N` looks like `{-|}` (capitalize)
- `\cxa` (looks like Q/A)

### Eclipse commands

Need to view the Eclipse commands. Plover commands are loosely based off of Eclipse commands.

## JSON Novelties

Plover has many program-specific commands that won't be applicable when converting to JSON.

- `{#KeyCombos}` like `{#Return Up Control_L(BackSpace)}`
- Carry capitalization `{~|}`
- Uppercase next word: `{<}`
- Retro commands `{*...}`
- Meta commands `{PLOVER:}`
- Mode commands `{MODE:}`
- Unicode characters may pose a problem.
