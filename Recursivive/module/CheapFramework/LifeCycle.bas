Attribute VB_Name = "LifeCycle"
Option Explicit
'=============================================================================
' Constants
'=============================================================================
Private Const INJECT_PREFIX = "inject_"
'=============================================================================
' Win32API
'=============================================================================
Declare Function GetAsyncKeyState Lib "User32.dll" (ByVal vKey As Long) As Long

'=============================================================================
' SubProc
'=============================================================================

' OnStart -> 再起処理 -> OnEndの呼び出しを行います
Public Sub ExecuteRecursive(ByVal dir As String)
    Dim res As Long
    If IsBlank(dir) = True Then
        MsgBox "ディレクトリが入力されていません", vbCritical
        Exit Sub
    End If
    res = (MsgBox(dir & "配下のファイル群を処理します", vbYesNo))
    Call OnStart
    
    If res = vbYes Then
        Call FolderGrep(Cells(5, 2).MergeArea(1, 1).value)
    End If
    
    Call OnEnd
End Sub


' 再起処理開始時に発行されるイベント
Private Sub OnStart()
    On Error Resume Next
    Call DebugLog("[FW]OnStart -> start")
    Call DebugLog("[FW]Invoke -> OnStart_" & Replace(Application.Caller, INJECT_PREFIX, ""))
    Application.Run ("OnStart_" & Replace(Application.Caller, INJECT_PREFIX, ""))
    Call DebugLog("[FW]OnStart -> end")
End Sub

' 再起処理開始時に発行されるイベント
Private Sub OnEnd()
    On Error Resume Next
    Call DebugLog("[FW]OnEnd -> start")
    Call DebugLog("[FW]Invoke -> OnEnd_" & Replace(Application.Caller, INJECT_PREFIX, ""))
    Application.Run ("OnEnd_" & Replace(Application.Caller, INJECT_PREFIX, ""))
    Call DebugLog("[FW]OnEnd -> end")
End Sub


'=============================================================================
' Function
'=============================================================================


' リフレクションによりDIを行います
Public Function Inject(ByVal path As String, ByRef file As Object) As Boolean

    On Error GoTo ErrorHandler
    Call DebugLog("[FW]Inject -> start")
    Call DebugLog("[FW]Invoke ->" & Replace(Application.Caller, INJECT_PREFIX, ""))
    Inject = Application.Run(Replace(Application.Caller, INJECT_PREFIX, ""), path, file)
    Call DebugLog("[FW]Inject -> end")

ErrorHandler:
    If Err.Number <> 0 Then
        Call ErrorLog("[エラー発生]：エラーコード = " & Err.Number & vbNewLine & Err.Description)
        MsgBox "[エラー発生]：エラーコード = " & Err.Number & vbNewLine & Err.Description
    End If
End Function

' ユーザーの終了通知
Public Function IsUserEndCall(ByVal isEnd As Boolean) As Boolean
    Dim res As String
    If isEnd = True Then
        res = MsgBox("終了しますか？", vbYesNo)
        IsUserEndCall = (res = vbYes)
        Exit Function
    End If
    IsUserEndCall = False
End Function


