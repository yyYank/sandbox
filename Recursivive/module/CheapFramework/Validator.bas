Attribute VB_Name = "Validator"
Option Explicit
Public Function IsBlank(ByVal value As String) As Boolean
    IsBlank = (Len(Trim(value)) = 0)
End Function

Public Function IsNotBlank(ByVal value As String) As Boolean
    IsNotBlank = (Len(Trim(value)) <> 0)
End Function

Public Function IsNotNumeric(ByVal value As String) As Boolean
    IsNotNumeric = Not IsNumeric(value)
End Function
