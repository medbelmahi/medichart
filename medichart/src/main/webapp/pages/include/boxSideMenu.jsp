<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="box sidemenu">
				<div class="block" id="section-menu">
					<ul id="Accordion" class="section menu">
						<li><a class="menuitem">Gestion des utilisateurs</a>
							<ul class="submenu">
								<li><a href='<s:property value="rootUrl" />users/add'>Ajouter</a></li>
								<li><a href='<s:property value="rootUrl"/>users/list'>Liste des utilisateurs</a></li>
								<li><a href='<s:property value="rootUrl"/>users/list'>Modifier ou Supprmier</a></li>
							</ul>
						</li>
						<li><a class="menuitem">Gestion des nodes</a>
							<ul class="submenu">
								<li><a href='<s:property value="rootUrl"/>nodes/add'>Ajouter</a></li>
								<li><a href='<s:property value="rootUrl"/>nodes/remove'>Supprimer</a></li>
								<li><a href='<s:property value="rootUrl"/>nodes/remove'>Liste des nodes</a></li>
								<li><a href='<s:property value="rootUrl"/>nodes/deleteAll'>Supprimer tous</a></li>
								<li><a href='<s:property value="rootUrl"/>nodes/load'>Charger un fichier CSV</a></li>
							</ul>
						</li>
						<li><a class="menuitem">Gestion interfaces logiques</a>
							<ul class="submenu">
								<li><a href='<s:property value="rootUrl"/>interfaces/add'>Ajouter</a></li>
								<li><a href='<s:property value="rootUrl"/>interfaces/remove'>Supprimer</a></li>
								<li><a href='<s:property value="rootUrl"/>interfaces/remove'>Liste des interfaces</a></li>
								<li><a href='<s:property value="rootUrl"/>interfaces/getAllActivated'>Activer/Désactiver</a></li>
								<li><a href='<s:property value="rootUrl"/>interfaces/deleteAll'>Supprimer tous</a></li>
								<li><a href='<s:property value="rootUrl"/>interfaces/load'>Charger un fichier CSV</a></li>
								<li><a href='<s:property value="rootUrl"/>interfaces/autorizedAlias'>Préfixe/Suffixe autorisés</a></li>
							</ul>
						</li>
						<li><a class="menuitem">Gestion interfaces physique</a>
							<ul class="submenu">
								<li><a href='<s:property value="rootUrl"/>interfacesPhysics/add'>Ajouter</a></li>
								<li><a href='<s:property value="rootUrl"/>interfacesPhysics/remove'>Supprimer</a></li>
								<li><a href='<s:property value="rootUrl"/>interfacesPhysics/remove'>Liste des interfaces</a></li>
								<li><a href='<s:property value="rootUrl"/>interfacesPhysics/deleteAll'>Supprimer tous</a></li>
							</ul>
						</li>
						<li><a class="menuitem">Gestion systeme</a>
							<ul class="submenu">
								<li><a href='<s:property value="rootUrl"/>system/status'>Activé/Désactivé</a></li>
<%-- 								<li><a href='<s:property value="rootUrl"/>system/ftp'>Configuration FTP</a></li> --%>
							</ul>
						</li>
					</ul>
				</div>
			</div>