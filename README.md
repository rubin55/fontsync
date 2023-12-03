# fontsync - a command-line utility for synchronizing font settings

On Linux and BSD systems you have a lot of graphical toolkits and interfaces.
With this abundance of options also comes an abundance of variance in how and
where to configure things.

In particular, this also has the unfortunate effect of font settings existing
all over the place, which hinders a consistent look. 'fontsync' aims to fix
that by providing a pluggable font-config manager for applications and GUI
toolkits you happen to use.

For now a quick note:

    sbt run

Doing the above creates a native binary under `target/scala-*/fontsync-out`.
Fontsync, a program that can synchronize font-settings between different
programs. Ideas and such.

## Some preliminary notes

`fontsync` is still in development and does not have a usable version yet. Here
are some preliminary ideas and notes about its construction.

### Language and libraries:

  * scala3
  * scala-native
  * scopt
  * os-lib
  * coulomb
  * circe
  * circe-scala-yaml
  * scala-xml

### General requirments

  * needs to be dpi aware
  * needs to be scaling-factor aware
  * needs to handle unit conversion
  * standard command interface to plugins
  * plugins can run commands, edit files, etc
  * option to do size only, or font name only

### What you would globally configure

  * mono font and size
  * sans font and size
  * serif font and size

### Plugins for applications that have font settings

  * gnome
  * gtk2
  * gtk3
  * gtk4
  * kde
  * qt5
  * qt6
  * sublimetext
  * jetbrains
  * vscode
  * vim
  * emacs
  * firefox
  * thunderbird
  * blender
  * netbeans
  * eclipse
  * chromium
  * calibre
  * imhex
