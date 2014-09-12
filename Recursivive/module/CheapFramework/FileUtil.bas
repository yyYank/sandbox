Attribute VB_Name = "FileUtil"

Option Explicit

'=============================================================================
' Constants
'=============================================================================
Private Const INJECT_PREFIX = "inject_"

'=============================================================================
' Enum
'=============================================================================
' 「ツール＞参照」でMicrosoft ActiveX Data Objects 6.1 Libraryを参照する場合は以下のEnumは不要
Public Enum StreamTypeEnum
  adTypeBinary = 1  '&H1
  adTypeText = 2  '&H2
End Enum

Public Enum StreamWriteEnum
  adWriteChar = 0  '&H0
  adWriteLine = 1  '&H1
  stWriteChar = 0  '&H0
  stWriteLine = 1  '&H1
End Enum

Public Enum SaveOptionsEnum
  adSaveCreateNotExist = 1  '&H1
  adSaveCreateOverWrite = 2  '&H2
End Enum

Public Enum StreamReadEnum
  adReadAll = -1  '&HFFFFFFFF
  adReadLine = -2  '&HFFFFFFFE
End Enum

'=============================================================================
' SubProc , Function
'=============================================================================
' エクスプローラーでディレクトリを決定
Public Function DecideDirectory()
    Dim res As Long
    With Application.FileDialog(msoFileDialogFolderPicker)
        If .Show = True Then
            DecideDirectory = .SelectedItems(1)
        End If
    End With
End Function
' 起点となるディレクトリから下の階層を再起的に処理します
Public Function FolderGrep(ByVal strTargetDir As String) As Boolean
    Dim fso As Object
    Dim folder As Object
    Dim subFolder As Object
    Dim file As Object
    
    Set fso = CreateObject("Scripting.FileSystemObject")
    Set folder = fso.GetFolder(strTargetDir)
    ' カレントフォルダ内のループ
    For Each file In folder.Files
        If Inject(strTargetDir, file) = False Then
            MsgBox "処理失敗", vbCritical
            Exit Function
        End If
        If IsUserEndCall(GetAsyncKeyState(vbKeyControl) And GetAsyncKeyState(vbKeyC)) Then
            Exit Function
        End If
    Next file

    ' サブフォルダ内のループ
    For Each subFolder In folder.SubFolders
        ' 再起処理呼び出し
        Call FolderGrep(subFolder.path)
    Next subFolder

    ' オブジェクト解放
    Set fso = Nothing
    Set folder = Nothing
End Function
' テキストファイルの読み込み
' デフォルトはUTF-8
Public Function LoadTextFile(ByVal fileName As String, Optional encode = "UTF-8") As String
    Dim stream As Object
    
    ' ファイルがない場合は空文字をリターン
    If dir(fileName) = "" Then
        LoadTextFile = ""
        Exit Function
    End If
    
    Set stream = CreateObject("ADODB.Stream")
    With stream
        .Type = adTypeText
        .Charset = encode
        .Open
        .LoadFromFile (fileName)
        LoadTextFile = .ReadText(adReadAll)
        .Close
    End With
    
    Set stream = Nothing
End Function

' テキストの出力
' デフォルトはUTF-8
Public Sub OutputTextFile(ByVal text As String, ByVal fileName As String, Optional addMode, Optional encode = "UTF-8")
    On Error GoTo ErrorHandler
    Dim stream As Object
    ' 追記モード処理
    text = IIf(IsMissing(addMode) = False, LoadTextFile(fileName, encode), "") & text
    ' 初期化
    Set stream = CreateObject("ADODB.Stream")
    With stream
        .Open
        .Type = adTypeText
        .Charset = encode
        .WriteText text, adWriteLine
        If UCase(encode) = "UTF-8" Then Call RemoveBOM(stream)
        .savetofile (fileName), adSaveCreateOverWrite
        .Close
    End With
    Set stream = Nothing
ErrorHandler:
    If Err.Number <> 0 Then
        Debug.Print ("[エラー発生]：エラーコード = " & Err.Number & vbNewLine & Err.Description)
        MsgBox "[エラー発生]：エラーコード = " & Err.Number & vbNewLine & Err.Description
    End If
    Set stream = Nothing
End Sub
' BOMを削除する
Private Sub RemoveBOM(ByRef stream As Object)
    Dim byteData() As Byte
    
    With stream
        .Position = 0
        .Type = adTypeBinary
        .Position = 3
        byteData = .read
        .Close
        .Open
        .write byteData
    End With
End Sub
