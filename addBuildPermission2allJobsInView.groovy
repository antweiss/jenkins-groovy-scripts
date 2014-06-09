
//get to the view under which all the jobs need to be modified
def view = Hudson.instance.getView(_vewName_).getView(_viewName_);
println (view.getDisplayName());
//this loops on subviews (remove this loop if doing inside of a sinlge list view)
for(subview in view.getViews())
{
  println (subview.name)
  for (item in subview.getItems())
  {
    PermProp = item.getProperty(hudson.security.AuthorizationMatrixProperty)
    perms = PermProp.getGrantedPermissions();
    for ( perm in perms ) {
      if (perm.key.getId() == "hudson.model.Item.Build")
      {
        println perm.key.getId();
        println perm.value;
		PermProp.add (perm.key, "USER_NAME");
      }
    }
}
}