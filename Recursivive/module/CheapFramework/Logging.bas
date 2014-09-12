Attribute VB_Name = "Logging"
Option Explicit
Private logLevel As Long
Private filePath As String
'=============================================================================
' SubProc
'=============================================================================
' DEBUG
Public Sub DebugLog(ByVal message As String)
    If logLevel = 1 Then
        Debug.Print "[" & Format(Now, "yyyy/MM/dd HH:mm:ss.SSS") & "][DEBUG]" & vbTab & message
        If IsNotBlank(filePath) = True Then
            Call OutputTextFile("[" & Format(Now, "yyyy/MM/dd HH:mm:ss.SSS") & "][DEBUG]" & vbTab & message, filePath, True)
        End If
    End If
End Sub
' INFO
Public Sub InfoLog(ByVal message As String)
    If logLevel <= 2 Then
        Debug.Print "[" & Format(Now, "yyyy/MM/dd HH:mm:ss.SSS") & "][INFO]" & vbTab & message
        If IsNotBlank(filePath) = True Then
            Call OutputTextFile("[" & Format(Now, "yyyy/MM/dd HH:mm:ss.SSS") & "][INFO]" & vbTab & message, filePath, True)
        End If
    End If
End Sub
' ERROR
Public Sub ErrorLog(ByVal message As String)
    If logLevel <= 3 Then
        Debug.Print "[" & Format(Now, "yyyy/MM/dd HH:mm:ss.SSS") & "][ERROR]" & vbTab & message
        If IsNotBlank(filePath) = True Then
            Call OutputTextFile("[" & Format(Now, "yyyy/MM/dd HH:mm:ss.SSS") & "][ERROR]" & vbTab & message, filePath, True)
        End If
    End If
End Sub
' FATAL
Public Sub FatalLog(ByVal message As String)
    If logLevel <= 4 Then
        Debug.Print "[" & Format(Now, "yyyy/MM/dd HH:mm:ss.SSS") & "][FATAL]" & vbTab & message
        If IsNotBlank(filePath) = True Then
            Call OutputTextFile("[" & Format(Now, "yyyy/MM/dd HH:mm:ss.SSS") & "][FATAL]" & vbTab & message, filePath, True)
        End If
    End If
End Sub

'=============================================================================
' Setter
'=============================================================================

Public Sub SetLogFilePath(ByVal val As String)
    filePath = val
End Sub
Public Sub SetLogLevel(ByVal val As String)
    logLevel = val
End Sub

