[![Build Status](https://travis-ci.com/soartech/soar-language-server.svg?branch=master)](https://travis-ci.com/soartech/soar-language-server)
[![Build status](https://ci.appveyor.com/api/projects/status/odm1cx7f8phh99pw/branch/master?svg=true)](https://ci.appveyor.com/project/soartech/soar-language-server/branch/master)
[![Dependabot Status](https://api.dependabot.com/badges/status?host=github&repo=soartech/soar-language-server)](https://dependabot.com)

# Soar Language Server

This project provides editor/IDE support for the [Soar
language](https://soar.eecs.umich.edu/) via the [Language Server
Protocol](https://langserver.org/).

## Features
* **Multiple IDE Support**: VSCode is the gold standard, and it has
  also been heavily tested in Emacs. Other IDEs that have been tried
  include Intellij, Eclipse, and Sublime; it works to varying degrees
  in these IDEs, depending on the completeness of their generic LSP
  support plugins.
* **Tcl Expansion**: The LSP will create a file (by default,
  `~tcl-expansion.soar`) that will show the Soar rules generated by
  any selected top level command that generates them (e.g., `sp` or
  other custom procs). "Selected" means the cursor is in the command
  or its arguments.
* **Go to Definition**: For Tcl variables and procs. Depending on your
  IDE and OS, this can be activated via Ctrl+left-click, right-click
  context menu, and/or F12.
* **Find references**: Find all references to a selected Tcl variable
  or proc. This may be accessible via a right-click context menu.
* **Hover for Tcl Variable Values**: Hovering over a Tcl variable will
  show the value that variables has at that point in the code.
* **Hover for Tcl Proc Documentation**: Hovering over a Tcl proc call
  will show the comments immediately preceding its definition.
* **Error and Warning Reporting**: All code is actually executed in an
  internal JSoar instance. If this produces any errors or warnings,
  these are captured and displayed within the IDE. The IDE attempts to
  continue past errors so that the analysis is as complete as
  possible.
* **Rename**: Tcl variables can be renamed, and the known instances
  are all updated.
* **Commenting selected text**: The current line or selected block can
  be commented/uncommented via Ctrl+/. This may vary depending on IDE
  and OS.
* **Autocomplete**: A list of Tcl procs will be shown when typing
  top-level commands or `[`. A list of Tcl variables is shown after
  typing a `$`.
* **Code Folding**: Comments, rules, and Tcl procs can be folded.

**A note on syntax highlighting**: This is not actually a built-in
feature (it is not directly supported by LSP), but some IDEs already
have plugins that provide Soar syntax highlighting. The VSCode plugin
automatically includes the other existing VSCode plugin for
you. Otherwise, you should use another existing plugin.

## Project Setup
In order for the Soar LSP to know how to load your agent, you need to
create a `soarAgents.json` file at the root of your workspace. This
file defines the agents that are present in the workspace, and which
one to use as the default. A default is needed because there is
currently no way to resolve conflicts for things like "Go to
Definition" when two different agents define the same variable. (Note
that currently only the default agent is actually analyzed.)

Here's an example:

```json
{
	"entryPoints": [
		{
			"path": "agent1/load.soar",
			"name": "agent1"
		},
		{
			"path": "agent2/load.soar",
			"name": "agent2"
		}
	],
	"active": "agent1"
}

```

The `entryPoints` list defines the name and start file for each
agent. There should be at least one entry in here. The `active` field
is the name of the agent to use as the default. This should match the
name of one of the entries in the `entryPoint` list.

If you modify this file, you may have to restart the LSP server (e.g.,
re-open your workspace or restart your IDE).

# Running

Download the latest build of the server from the [releases
page](./releases), then find instructions for your editor in the
[integrations](./integrations) pages.

# Building

First, clone this repository:

```bash
$ git clone https://github.com/soartech/soar-language-server
$ cd soar-language-server
```

We currently rely on a fork of JSoar which we include as a git
submodule, which must be initialized like so:

```bash
# The first time you clone this repository:
$ git submodule update --init

# If you pull changes that move JSoar to a new commit:
$ git submodule update
```

After the initial setup, the language server can be built with
Gradle. The Gradle wrapper script is included:

```bash
$ ./gradlew test
$ ./gradlew installDist
```

This will create an executable at
`./build/install/soar-language-server/bin/soar-language-server`.

Before pushing changes, please run the Google Java formatter:

```bash
$ ./gradlew googleJavaFormat
```

# Clients

We don't yet have any officially supported plugins. Work in progress
plugins can be found in the [integrations](./integrations) directory.
